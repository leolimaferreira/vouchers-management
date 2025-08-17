package com.vouchers.service;

import com.vouchers.dto.user.CreateUserDTO;
import com.vouchers.dto.user.UserSearchResultDTO;
import com.vouchers.exception.DuplicatedRegistryException;
import com.vouchers.exception.NotFoundException;
import com.vouchers.mapper.UserMapper;
import com.vouchers.model.User;
import com.vouchers.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserSearchResultDTO create(CreateUserDTO dto) {
        User user = userMapper.toUser(dto);
        validateCreationAndUpdate(user);
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

    private void validateCreationAndUpdate(User user) {
        if (isDuplicateUser(user)) {
            throw new DuplicatedRegistryException("User already registered");
        }
    }

    private boolean isDuplicateUser(User user) {
        Optional<User> userOptional = userRepository.findByEmail(user.getEmail());

        if (user.getId() == null) {
            return userOptional.isPresent();
        }

        return !user.getId().equals(userOptional.get().getId());
    }
}
