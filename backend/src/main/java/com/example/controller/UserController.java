package com.example.controller;

import com.example.common.ApiResponse;
import com.example.common.ResultCode;
import com.example.entity.User;
import com.example.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        log.info("获取所有用户列表");
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public ApiResponse<User> getUserById(@PathVariable Long id) {
        log.info("根据ID获取用户: {}", id);
        return userService.findById(id)
                .map(ApiResponse::success)
                .orElse(ApiResponse.error(ResultCode.USER_NOT_FOUND));
    }

    @PostMapping
    public ApiResponse<User> createUser(@RequestBody User user) {
        log.info("创建用户请求: {}", user);
        // git咋回事
        // 验证必要字段
        if (user.getName() == null || user.getName().trim().isEmpty()) {
            log.warn("创建用户失败：用户名为空");
            return ApiResponse.validateFailed("用户名不能为空");
        }

        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            log.warn("创建用户失败：邮箱为空");
            return ApiResponse.validateFailed("邮箱不能为空");
        }

        try {
            boolean success = userService.saveUser(user);
            if (success) {
                log.info("用户创建成功: {}", user);
                return ApiResponse.success("用户创建成功", user);
            } else {
                log.error("用户创建失败：数据库保存失败");
                return ApiResponse.error("用户创建失败");
            }
        } catch (Exception e) {
            log.error("创建用户时发生异常: {}", e.getMessage(), e);
            throw e; // 让全局异常处理器处理
        }
    }

    @PutMapping("/{id}")
    public ApiResponse<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        log.info("更新用户请求: ID={}, 数据={}", id, userDetails);

        return userService.findById(id)
                .<ApiResponse<User>>map(user -> {
                    userDetails.setId(id);
                    boolean success = userService.saveUser(userDetails);
                    if (success) {
                        log.info("用户更新成功: {}", userDetails);
                        return ApiResponse.success("用户更新成功", userDetails);
                    } else {
                        log.error("用户更新失败：数据库保存失败");
                        return ApiResponse.<User>error("用户更新失败");
                    }
                })
                .orElse(ApiResponse.<User>error(ResultCode.USER_NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteUser(@PathVariable Long id) {
        log.info("删除用户请求: ID={}", id);

        // 先检查用户是否存在
        if (userService.findById(id).isEmpty()) {
            log.warn("删除用户失败：用户不存在, ID={}", id);
            return ApiResponse.error(ResultCode.USER_NOT_FOUND);
        }

        boolean deleted = userService.deleteById(id);
        if (deleted) {
            log.info("用户删除成功: ID={}", id);
            return ApiResponse.success("用户删除成功", null);
        } else {
            log.error("用户删除失败：数据库操作失败, ID={}", id);
            return ApiResponse.error("用户删除失败");
        }
    }

    @GetMapping("/health")
    public String health() {
        log.debug("健康检查请求");
        return "User service is running!";
    }
}
