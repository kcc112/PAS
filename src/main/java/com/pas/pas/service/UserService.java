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
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements IUserService {

    private  final IUserRepository userRepository;

    @Autowired
    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUser(User user) {
        String userName = user.getUserName();
        String userSurname = user.getUserSurname();
        String userType = user.getUserType();
        UUID id = user.getUserId();

        switch (user.getUserType()) {
            case "Admin":
                user = new Admin(userName, userSurname, userType, id, true);
                userRepository.addUser(user);
                break;
            case "Client":
                user = new Client(userName, userSurname, userType, id, true);
                userRepository.addUser(user);
                break;
            case "ResourceAdministrator":
                user = new ResourceAdministrator(userName, userSurname, userType, id, true);
                userRepository.addUser(user);
                break;
            default:
                break;
        }
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @Override
    public void destroyUser(UUID id) {
        userRepository.destroyUser(id);
    }

    @Override
    public void updateUser(User user) {
        Optional<User> currentUser = userRepository.selectUserById(user.getUserId());

        if (currentUser.isPresent()) {

            if(!user.getUserName().isBlank()) {
                String name = user.getUserName();
                currentUser.get().setUserName(name);
            }

            if(!user.getUserSurname().isBlank()) {
                String surname = user.getUserSurname();
                currentUser.get().setUserSurname(surname);
            }
            userRepository.updateUser(currentUser.get());
        }
    }

    @Override
    public List<User> getAllActiveClients() {
        return userRepository.getAllActiveClients();
    }

    @Override
    public Optional<User> selectUserById(UUID id) {
        return userRepository.selectUserById(id);
    }

    @Override
    public void activateOrDeactivateUser(UUID id) {
        Optional<User> userToChange = userRepository.selectUserById(id);
        userToChange.ifPresent(user -> {
            if (user.getIsActive()) {
                user.setActive(false);
            } else {
                user.setActive(true);
            }
        });
    }
}
