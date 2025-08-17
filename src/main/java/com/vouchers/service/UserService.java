package com.vouchers.service;

import com.vouchers.model.User;
import com.vouchers.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void create(User user) {
        userRepository.save(user);
    }
}
