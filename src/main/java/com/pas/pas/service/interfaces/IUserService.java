package com.pas.pas.service.interfaces;

import com.pas.pas.model.users.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IUserService {
    List<User> getAllUsers();
    void addUser(User user);
    void destroyUser(UUID id);
    void updateUser(User user);
    void activateOrDeactivateUser(UUID id);
    List<User> getAllAdmins();
    List<User> getAllClients();
    Optional<User> selectUserById(UUID id);
}
