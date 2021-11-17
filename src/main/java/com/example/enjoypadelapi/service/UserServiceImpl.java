package com.example.enjoypadelapi.service;

import com.example.enjoypadelapi.domain.User;
import com.example.enjoypadelapi.exception.UserNotFoundException;
import com.example.enjoypadelapi.repository.TeamRepository;
import com.example.enjoypadelapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.print.attribute.UnmodifiableSetException;
import java.util.List;

public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    TeamRepository teamRepository;

    @Override
    public List<User> findAll() {
        List<User> users = userRepository.findAll();
        return users;
    }

    @Override
    public User findById(long id) throws UserNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new UserNotFoundException());
        return user;
    }

    @Override
    public User addUser(User newUser) {
        User user = userRepository.save(newUser);
        return user;
    }

    @Override
    public User deleteUser(long id) throws UserNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new UserNotFoundException());
        userRepository.delete(user);
        return user;
    }

    @Override
    public User modififyUser(long id, User newUser) {
        return null;
    }
}
