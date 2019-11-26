package com.pas.pas.repository;

import com.pas.pas.model.users.Admin;
import com.pas.pas.model.users.Client;
import com.pas.pas.model.users.ResourceAdministrator;
import com.pas.pas.model.users.User;
import com.pas.pas.repository.interfaces.IUserRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class UserRepository implements IUserRepository {

    private List<User> users;

    public UserRepository() {
        this.users = new ArrayList<>();
        users.add(new Admin("Kamil", "Celejewski","Admin", randUUID(), true));
        users.add(new Client("Marcin", "Morawski","Client", randUUID(), true));
        users.add(new ResourceAdministrator("Szymon", "Dobrowolski","ResourceAdministrator", randUUID(), true));
    }

    private UUID randUUID() {
        return UUID.randomUUID();
    }

    @Override
    public void addUser(UUID id, User user) {
        user.setUserId(id);
        user.setActive(true);
        users.add(user);
    }

    @Override
    public List<User> getAllUsers() {
        return users;
    }

    @Override
    public Optional<User> selectUserById(UUID id) {
        return users.stream()
                .filter(user -> user.getUserId().equals(id))
                .findFirst();
    }

    @Override
    public void destroyUser(UUID id) {
        Optional<User> userToDelete = selectUserById(id);
        userToDelete.ifPresent(user -> users.remove(user));
    }

    @Override
    public void updateUser(User user) {
        Optional<User> userCurrent = selectUserById(user.getUserId());
        if (userCurrent.isPresent()) {
            int indexOfPersonToUpdate = users.indexOf(userCurrent.get());
            users.set(indexOfPersonToUpdate, user);
        }
    }


    @Override
    public List<User> getAllAdmins() {
        List<User> admins = new ArrayList<>();
        for (User user : users) {
            if (user.getClass().equals(Admin.class)) {
                admins.add(user);
            }
        }
        return admins;
    }

    @Override
    public List<User> getAllClients() {
        List<User> clients = new ArrayList<>();
        for (User user : users) {
            if (user.getClass().equals(Client.class)) {
                clients.add(user);
            }
        }
        return clients;
    }
}
