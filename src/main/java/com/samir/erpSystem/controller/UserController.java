package com.samir.erpSystem.controller;

import com.samir.erpSystem.dto.request.ReqLogin;
import com.samir.erpSystem.dto.request.ReqUser;
import com.samir.erpSystem.dto.response.RespUser;
import com.samir.erpSystem.dto.response.Response;
import com.samir.erpSystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping("/login")
    public Response<RespUser> login(@RequestBody ReqLogin reqLogin) {
        return userService.login(reqLogin);
    }
}
