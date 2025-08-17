package com.vouchers.mapper;

import com.vouchers.dto.CreateUserDTO;
import com.vouchers.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    public User toUser(CreateUserDTO createUserDTO);

    public CreateUserDTO toCreateUserDTO(User user);
}
