package com.github.yuzhian.zero.authorization.controller;

import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/oauth/confirm_access")
    public String getAccessConfirmation(@RequestAttribute("authorizationRequest") AuthorizationRequest authorizationRequest, Model model) {
        model.addAttribute("clientId", authorizationRequest.getClientId());
        model.addAttribute("scope", authorizationRequest.getScope());
        return "grant";
    }
}
