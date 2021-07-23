package com.github.yuzhian.zero.authorization.service.impl;

import com.github.yuzhian.zero.authorization.entity.Account;
import com.github.yuzhian.zero.authorization.entity.Permission;
import com.github.yuzhian.zero.authorization.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService, Serializable {
    private final AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountService.getAccount(username);
        if (null == account) throw new UsernameNotFoundException("用户名不存在");

        String[] authorities = Stream.concat(
                account.getRoles().stream().flatMap(role -> role.getPermissions().stream()).map(Permission::getPermission),
                account.getRoles().stream().map(role -> "ROLE_" + role.getRole())
        ).distinct().toArray(String[]::new);

        return User.builder()
                .username(account.getUserId())
                .password(account.getPassword())
                .authorities(authorities)
                .build();
    }
}
