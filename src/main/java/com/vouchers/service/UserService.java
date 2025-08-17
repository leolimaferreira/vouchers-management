package com.vouchers.service;

import com.vouchers.dto.user.CreateUserDTO;
import com.vouchers.dto.user.UpdateUserDTO;
import com.vouchers.dto.user.UserSearchResultDTO;
import com.vouchers.exception.DuplicatedRegistryException;
import com.vouchers.exception.NotFoundException;
import com.vouchers.mapper.UserMapper;
import com.vouchers.model.User;
import com.vouchers.repository.UserRepository;
import com.vouchers.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserValidator userValidator;

    public UserSearchResultDTO create(CreateUserDTO dto) {
        User user = userMapper.toUser(dto);
        userValidator.validateCreationAndUpdate(user);
        userRepository.save(user);
        return userMapper.toUserSearchResultDTO(user);
    }

    public UserSearchResultDTO findUserById(UUID id) {
        User user = userRepository.findById(id).orElseThrow( () -> new NotFoundException("User with ID: " + id + " not found"));
        return userMapper.toUserSearchResultDTO(user);
    }

    public List<UserSearchResultDTO> findAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toUserSearchResultDTO)
                .toList();
    }

    public void updateUser(UUID id, UpdateUserDTO dto) {
        User user = userRepository.findById(id).orElseThrow( () -> new NotFoundException("User with ID: " + id + " not found"));
        userMapper.updateUserFromDto(dto, user);
        userValidator.validateCreationAndUpdate(user);
        userRepository.save(user);
    }

    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }
}
