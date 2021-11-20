package com.example.enjoypadelapi.service;

import com.example.enjoypadelapi.domain.Team;
import com.example.enjoypadelapi.domain.User;
import com.example.enjoypadelapi.exception.TeamNotFoundException;
import com.example.enjoypadelapi.exception.UserNotFoundException;
import com.example.enjoypadelapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User findById(long id) throws UserNotFoundException;
    User addUser(User newUser);
    User deleteUser(long id) throws UserNotFoundException;
    User modififyUser(long id, User newUser);

    Team addUserToTeam(long user_id, long team_id) throws UserNotFoundException, TeamNotFoundException;

    List<Team> findUserTeams(long id) throws UserNotFoundException;

    Team deleteUserToTeam(long user_id, long team_id) throws UserNotFoundException, TeamNotFoundException;
}
