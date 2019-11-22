package com.pas.pas.repository.interfaces;

import com.pas.pas.model.users.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IUserRepository {

    void addUser(UUID id, User user);
    void destroyUser(UUID id);
    void updateUser(User user);
    Optional<User> selectUserById(UUID id);
    List<User> getAllClients();
    List<User> getAllAdmins();
    List<User> getAllUsers();

    default void addUser(User user) {
        UUID id = UUID.randomUUID();
        addUser(id, user);
    }
}
