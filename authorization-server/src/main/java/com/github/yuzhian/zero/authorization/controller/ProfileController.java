package com.github.yuzhian.zero.authorization.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Slf4j
@Profile("dev")
@RestController
@RequestMapping("profile")
public class ProfileController {

    @GetMapping("/principal")
    public Principal user(Principal principal) {
        return principal;
    }
}
