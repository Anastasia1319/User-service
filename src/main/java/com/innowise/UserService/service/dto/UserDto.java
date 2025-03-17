package com.innowise.UserService.service.dto;

import lombok.Data;


@Data
public class UserDto {

    private long id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;
}
