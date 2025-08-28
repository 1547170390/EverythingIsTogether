package com.example.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 全局响应处理器
 * 自动将控制器返回的数据包装成统一的ApiResponse格式
 * 
 * @author xuzili
 */
@Slf4j
@ControllerAdvice(basePackages = "com.example.controller")
public class GlobalResponseAdvice implements ResponseBodyAdvice<Object> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        // 如果返回类型已经是ApiResponse，则不需要再次封装
        if (returnType.getParameterType().equals(ApiResponse.class)) {
            return false;
        }
        
        // 如果是错误页面或Spring Boot默认错误处理，不进行封装
        String methodName = returnType.getMethod() != null ? returnType.getMethod().getName() : "";
        if (methodName.contains("error") || methodName.contains("Error")) {
            return false;
        }
        
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {
        
        // 获取请求路径用于日志
        String requestPath = request.getURI().getPath();
        
        try {
            // 如果返回值已经是ApiResponse类型，直接返回
            if (body instanceof ApiResponse) {
                log.debug("Response already wrapped for path: {}", requestPath);
                return body;
            }

            // 如果返回值是String类型，需要特殊处理，因为StringHttpMessageConverter会直接处理
            if (body instanceof String) {
                response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
                ApiResponse<String> apiResponse = ApiResponse.success((String) body);
                return objectMapper.writeValueAsString(apiResponse);
            }

            // 其他情况，封装成ApiResponse
            ApiResponse<Object> apiResponse = ApiResponse.success(body);
            log.debug("Response wrapped successfully for path: {}", requestPath);
            return apiResponse;
            
        } catch (JsonProcessingException e) {
            log.error("Error processing JSON response for path: {}", requestPath, e);
            return ApiResponse.error("响应数据处理失败");
        } catch (Exception e) {
            log.error("Unexpected error in GlobalResponseAdvice for path: {}", requestPath, e);
            return ApiResponse.error("系统内部错误");
        }
    }
}
