package com.innowise.UserService.service.impl;

import com.innowise.UserService.data.UserRepository;
import com.innowise.UserService.data.entity.User;
import com.innowise.UserService.exceptions.NotFoundException;
import com.innowise.UserService.exceptions.ValidationException;
import com.innowise.UserService.service.UserService;
import com.innowise.UserService.service.dto.UserDto;
import com.innowise.UserService.service.mapping.Converter;
import com.innowise.UserService.service.validation.PasswordValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final Converter converter;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UserDto> getAll() {
        return userRepository.findAll()
                .stream()
                .map(converter::toUserDto)
                .toList();
    }

    @Override
    public UserDto getById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User with id " + id + " not found"));
        return converter.toUserDto(user);
    }

    @Override
    public UserDto save(UserDto userDto) {
        validateUser(userDto);
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User saved = userRepository.saveAndFlush(converter.toUserEntity(userDto));
        return converter.toUserDto(saved);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    private void validateUser(UserDto userDto) {

        PasswordValidator.validatePassword(userDto.getPassword());

        if (userDto.getId() == 0) {
            User user = userRepository.findByEmail(userDto.getEmail());
            if (user != null) {
                throw new ValidationException("User with email " + userDto.getEmail() + " already exists");
            }
        }
    }
}
