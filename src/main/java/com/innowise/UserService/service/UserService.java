package com.innowise.UserService.service;

import com.innowise.UserService.service.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getAll();
    UserDto getById(Long id);
    UserDto save(UserDto userDto);
    void delete(Long id);
}
