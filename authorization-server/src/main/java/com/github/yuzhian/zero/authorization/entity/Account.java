package com.github.yuzhian.zero.authorization.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "sys_account")
@SQLDelete(sql = "update sys_account set del_flag = 1 where user_id = ?")
@Where(clause = "del_flag = 0")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
// 账号
public class Account {
    // 用户主键
    @Id
    private String userId;

    // 登录名
    private String username;

    // 邮箱登陆
    private String email;

    // 手机登录
    private String phone;

    // 密码
    @JsonIgnore
    private String password;

    // 删除标记
    @JsonIgnore
    private Boolean delFlag;

    // 角色集
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "sys_account_role", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private List<Role> roles;
}
