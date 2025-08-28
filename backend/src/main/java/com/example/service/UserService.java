package com.example.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.User;
import com.example.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService extends ServiceImpl<UserMapper, User> {

    public List<User> findAll() {
        return list();
    }

    public Optional<User> findById(Long id) {
        return Optional.ofNullable(getById(id));
    }

    @Override
    public boolean save(User user) {
        return super.saveOrUpdate(user);
    }

    public boolean deleteById(Long id) {
        return removeById(id);
    }
}
