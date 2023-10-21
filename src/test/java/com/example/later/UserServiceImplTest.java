package com.example.later;

import com.example.later.enums.UserState;
import com.example.later.user.dto.UserDto;
import com.example.later.user.mapper.UserMapper;
import com.example.later.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Transactional
@SpringBootTest
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserServiceImplTest {
    private final UserService service;
    private final UserMapper mapper;

    @Test
    void saveUser() {
        UserDto userDto = makeUserDto("some@email.com", "Пётр", "Иванов");
        UserDto user = service.saveUser(userDto);

        assertThat(user.getId(), notNullValue());
        assertThat(user.getFirstName(), equalTo(userDto.getFirstName()));
        assertThat(user.getLastName(), equalTo(userDto.getLastName()));
        assertThat(user.getEmail(), equalTo(userDto.getEmail()));
        assertThat(user.getState(), equalTo(userDto.getState()));
        assertThat(user.getRegistrationDate(), notNullValue());
    }

    @Test
    void getUsers() {
        UserDto userDto = makeUserDto("someone@ya.ru", "Георгий", "Петрович");
        UserDto user = service.saveUser(userDto);
        List<UserDto> usersList = service.getAllUsers();

        assertThat(usersList.size(), equalTo(1));
        assertThat(usersList, hasItem(user));
    }

    private UserDto makeUserDto(String email, String firstName, String lastName) {
        UserDto dto = new UserDto();
        dto.setEmail(email);
        dto.setFirstName(firstName);
        dto.setLastName(lastName);
        dto.setRegistrationDate(String.valueOf(LocalDateTime.now()));
        dto.setState(UserState.ACTIVE);
        return dto;
    }
}
