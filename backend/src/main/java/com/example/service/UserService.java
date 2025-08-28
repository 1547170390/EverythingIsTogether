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

    /**
     * 保存用户（新增或更新）
     * 使用MyBatis-Plus的saveOrUpdate方法，会自动判断是插入还是更新
     * @param user 用户实体
     * @return 是否保存成功
     */
    public boolean saveUser(User user) {
        return saveOrUpdate(user);
    }

    public boolean deleteById(Long id) {
        return removeById(id);
    }
}
