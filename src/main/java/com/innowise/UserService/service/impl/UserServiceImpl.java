package com.innowise.UserService.service.impl;

import com.innowise.UserService.data.UserRepository;
import com.innowise.UserService.data.entity.User;
import com.innowise.UserService.exceptions.NotFoundException;
import com.innowise.UserService.service.UserService;
import com.innowise.UserService.service.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ConverterService converterService;

    @Override
    public List<UserDto> getAll() {
        return userRepository.findAll()
                .stream()
                .map(converterService::toUserDto)
                .toList();
    }

    @Override
    public UserDto getById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User with id " + id + " not found"));
        return converterService.toUserDto(user);
    }

    @Override
    public UserDto save(UserDto userDto) {
        User saved = userRepository.saveAndFlush(converterService.toUserEntity(userDto));
        return converterService.toUserDto(saved);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
