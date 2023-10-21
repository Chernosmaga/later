package com.example.later.user.service;

import com.example.later.user.dto.UserDto;
import com.example.later.user.mapper.UserMapper;
import com.example.later.user.model.User;
import com.example.later.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final UserMapper userMapper;

    @Transactional
    @Override
    public List<UserDto> getAllUsers() {
        return repository.findAll().stream().map(userMapper::toUserDto).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public UserDto saveUser(UserDto user) {
        User thisUser = repository.save(userMapper.toUser(user));
        return userMapper.toUserDto(thisUser);
    }
}