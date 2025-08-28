package com.example.controller;

import com.example.entity.User;
import com.example.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    
    private final UserService userService;
    
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        // 添加调试日志
        System.out.println("接收到的用户数据: " + user);
        System.out.println("用户名: " + user.getName());
        System.out.println("邮箱: " + user.getEmail());
        
        // 验证必要字段
        if (user.getName() == null || user.getName().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("错误：用户名不能为空。请确保JSON中包含name字段，例如：{\"name\":\"用户名\",\"email\":\"email@example.com\"}");
        }
        
        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("错误：邮箱不能为空");
        }
        
        try {
            userService.saveUser(user);
            System.out.println("用户创建成功: " + user);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            System.err.println("保存用户时出错: " + e.getMessage());
            return ResponseEntity.status(500).body("保存用户失败: " + e.getMessage());
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        return userService.findById(id)
                .map(user -> {
                    userDetails.setId(id);
                    userService.saveUser(userDetails);
                    return ResponseEntity.ok(userDetails);
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        boolean deleted = userService.deleteById(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("User service is running!");
    }
}
