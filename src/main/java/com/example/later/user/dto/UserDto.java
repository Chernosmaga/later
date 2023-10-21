package com.example.later.user.dto;

import com.example.later.enums.UserState;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    @Email
    private String email;
    private String registrationDate;
    @JsonFormat(pattern = "yyyy.MM.dd HH:mm")
    private String dateOfBirth;
    private UserState state;
}
