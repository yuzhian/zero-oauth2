package com.github.yuzhian.zero.client.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Profile("dev")
@Controller
public class IndexController {
    @RequestMapping("/index")
    public String userinfo(Model model, Principal principal) throws JsonProcessingException {
        model.addAttribute("principal", new ObjectMapper().writeValueAsString(principal));
        return "index";
    }
}
