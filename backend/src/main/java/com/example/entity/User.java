package com.example.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@TableName("users")
public class User {

    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户名字段
     * 映射到数据库的username字段
     * JsonProperty 用于JSON序列化/反序列化时的字段名映射
     */
    @TableField(value = "username", insertStrategy = FieldStrategy.IGNORED)
    @JsonProperty("username")  // JSON中使用"username"，Java中使用name
    private String name;

    /**
     * 邮箱字段
     */
    private String email;
}
