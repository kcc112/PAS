package com.pas.pas.service;

import com.pas.pas.model.users.Admin;
import com.pas.pas.model.users.Client;
import com.pas.pas.model.users.ResourceAdministrator;
import com.pas.pas.model.users.User;
import com.pas.pas.repository.interfaces.IUserRepository;
import com.pas.pas.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService implements IUserService {

    private  final IUserRepository userRepository;

    @Autowired
    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUser(User user) {
        switch (user.getUserType()) {
            case "Admin":
                user = new Admin(user.getUserName(), user.getUserSurname(), user.getUserType(), user.getId());
                userRepository.addUser(user);
                break;
            case "Client":
                user = new Client(user.getUserName(), user.getUserSurname(), user.getUserType(), user.getId());
                userRepository.addUser(user);
                break;
            case "ResourceAdministrator":
                user = new ResourceAdministrator(user.getUserName(), user.getUserSurname(), user.getUserType(), user.getId());
                userRepository.addUser(user);
                break;
            default:
                break;
        }
    }

    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @Override
    public void destroyUser(UUID id) {
        userRepository.destroyUser(id);
    }
}
