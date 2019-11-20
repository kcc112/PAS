package com.pas.pas.service.interfaces;

import com.pas.pas.model.users.User;

import java.util.List;
import java.util.UUID;

public interface IUserService {
    List<User> getAllUsers();
    void addUser(User user);
    void destroyUser(UUID id);
}
