package com.example.demo.domain;


import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UserInfo {
    private Integer id;
    @NotNull
    @Min(value = 3,message = "长度不能小于3")
    private Integer userId;
    @NotBlank(message = "用户名不能为空")
    private String userName;
    @NotBlank(message = "密码不能为空")
    @Length(min = 8,message = "长度不能小于8")
    private String password;
    private String picture;

}