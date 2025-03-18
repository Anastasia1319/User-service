package com.innowise.UserService.service;

import com.innowise.UserService.service.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface UserService {
    Page<UserDto> getAll(Pageable pageable);
    UserDto getById(Long id);
    UserDto save(UserDto userDto);
    void delete(Long id);
}
