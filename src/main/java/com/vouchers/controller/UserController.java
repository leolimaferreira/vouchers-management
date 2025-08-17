package com.vouchers.controller;

import com.vouchers.dto.CreateUserDTO;
import com.vouchers.mapper.UserMapper;
import com.vouchers.model.User;
import com.vouchers.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping
    public void createUser(@Valid @RequestBody CreateUserDTO dto) {
        User user = userMapper.toUser(dto);
        userService.create(user);
    }
}
