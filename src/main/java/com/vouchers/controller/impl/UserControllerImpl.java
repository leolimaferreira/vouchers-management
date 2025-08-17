package com.vouchers.controller.impl;

import com.vouchers.controller.GenericController;
import com.vouchers.dto.user.CreateUserDTO;
import com.vouchers.dto.user.UserSearchResultDTO;
import com.vouchers.mapper.UserMapper;
import com.vouchers.model.User;
import com.vouchers.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserControllerImpl implements GenericController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserSearchResultDTO> createUser(@Valid @RequestBody CreateUserDTO dto) {
        UserSearchResultDTO user = userService.create(dto);
        URI location = generateHeaderLocation(user.id());
        return ResponseEntity.created(location)
                .body(user);
    }
}
