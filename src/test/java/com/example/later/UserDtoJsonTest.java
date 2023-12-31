package com.example.later;

import com.example.later.enums.UserState;
import com.example.later.user.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


@JsonTest
public class UserDtoJsonTest {

    @Autowired
    private JacksonTester<UserDto> json;

    @Test
    void testUserDto() throws Exception {
        UserDto userDto = new UserDto(
                1L,
                "John",
                "Doe",
                "john.doe@mail.com",
                "2022.07.03 19:55:00",
                "2000.01.01 15:50:00",
                UserState.ACTIVE);

        JsonContent<UserDto> result = json.write(userDto);

        assertThat(result).extractingJsonPathNumberValue("$.id").isEqualTo(1);
        assertThat(result).extractingJsonPathStringValue("$.firstName").isEqualTo("John");
        assertThat(result).extractingJsonPathStringValue("$.lastName").isEqualTo("Doe");
        assertThat(result).extractingJsonPathStringValue("$.email").isEqualTo("john.doe@mail.com");
    }
}