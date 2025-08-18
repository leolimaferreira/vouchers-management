package com.vouchers.mapper;

import com.vouchers.dto.user.CreateUserDTO;
import com.vouchers.dto.user.UpdateUserDTO;
import com.vouchers.dto.user.UserSearchResultDTO;
import com.vouchers.model.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser(CreateUserDTO createUserDTO);

    UserSearchResultDTO toUserSearchResultDTO(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFromDto(UpdateUserDTO dto, @MappingTarget User user);
}
