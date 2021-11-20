package com.example.enjoypadelapi.service;

import com.example.enjoypadelapi.domain.Team;
import com.example.enjoypadelapi.domain.User;
import com.example.enjoypadelapi.exception.TeamNotFoundException;
import com.example.enjoypadelapi.exception.UserNotFoundException;
import com.example.enjoypadelapi.repository.TeamRepository;
import com.example.enjoypadelapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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

    @Override
    public Team addUserToTeam(long user_id, long team_id) throws UserNotFoundException, TeamNotFoundException {
        User user = userRepository.findById(user_id)
                .orElseThrow(()->new UserNotFoundException());
        Team team = teamRepository.findById(team_id)
                .orElseThrow(()->new TeamNotFoundException());
        if (team.getUsers().size() < 2){
            user.getTeams().add(team);
            team.getUsers().add(user);
            userRepository.save(user);
            teamRepository.save(team);
            return team;
        } else {
            return null;
        }
    }

    @Override
    public List<Team> findUserTeams(long id) throws UserNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(()->new UserNotFoundException());
        List<Team> teams = user.getTeams();
        return teams;
    }

    @Override
    public Team deleteUserToTeam(long user_id, long team_id) throws UserNotFoundException, TeamNotFoundException {
        User user = userRepository.findById(user_id)
                .orElseThrow(()->new UserNotFoundException());
        Team team = teamRepository.findById(team_id)
                .orElseThrow(()->new TeamNotFoundException());
        user.getTeams().remove(team);
        team.getUsers().remove(user);
        userRepository.save(user);
        teamRepository.save(team);
        return team;
    }
}
