package com.example.enjoypadelapi.service;

import com.example.enjoypadelapi.domain.User;
import com.example.enjoypadelapi.repository.TeamRepository;
import com.example.enjoypadelapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    TeamRepository teamRepository;

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findById(long id) {
        return null;
    }

    @Override
    public User addUser(User newUser) {
        return null;
    }

    @Override
    public User deleteUser(long id) {
        return null;
    }

    @Override
    public User modififyUser(long id, User newUser) {
        return null;
    }
}
