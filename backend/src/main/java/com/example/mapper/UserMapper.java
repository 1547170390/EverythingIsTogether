package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据姓名查询用户
     * 该方法的 SQL 实现在 /resources/mapper/UserMapper.xml 文件中
     * @param name 用户名
     * @return 用户实体
     */
    User findByName(String name);
}
