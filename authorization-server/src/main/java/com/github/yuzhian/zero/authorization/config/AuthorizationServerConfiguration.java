package com.github.yuzhian.zero.authorization.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

/**
 * 授权服务配置
 */
@Configuration
@RequiredArgsConstructor
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
        ;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("hello")
                .secret("{bcrypt}$2a$10$24g7OHwy1u6a6v3jVd2jpOejB972sh1CnStDzkDIh2XVD4W7QtXDm")
                .authorizedGrantTypes("authorization_code", "password", "implicit", "client_credentials", "refresh_token")
                .scopes("Profile", "Followers")
                .redirectUris("https://oauth.pstmn.io/v1/callback", "http://localhost:7000/login/oauth2/code/zero", "https://www.baidu.com")
        ;
    }
}
