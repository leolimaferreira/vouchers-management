package com.vouchers.validator;

import com.vouchers.exception.DuplicatedRegistryException;
import com.vouchers.model.User;
import com.vouchers.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserValidator {

    private final UserRepository userRepository;

    public void validateCreationAndUpdate(User user) {
        if (isDuplicateUser(user)) {
            throw new DuplicatedRegistryException("User already registered");
        }
    }

    private boolean isDuplicateUser(User user) {
        Optional<User> userOptional = userRepository.findByEmail(user.getEmail());
        if (user.getId() == null) {
            return userOptional.isPresent();
        }
        return userOptional.isPresent() && !user.getId().equals(userOptional.get().getId());
    }
}
