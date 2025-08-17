package com.vouchers.controller.impl;

import com.vouchers.controller.GenericController;
import com.vouchers.dto.user.CreateUserDTO;
import com.vouchers.dto.user.UserSearchResultDTO;
import com.vouchers.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

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

    @GetMapping("/{id}")
    public ResponseEntity<UserSearchResultDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.findUserById(id));
    }

    @GetMapping
    public ResponseEntity<List<UserSearchResultDTO>> findAll() {
        return ResponseEntity.ok(userService.findAllUsers());
    }
}
