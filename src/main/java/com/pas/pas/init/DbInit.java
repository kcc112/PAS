package com.pas.pas.init;

import com.pas.pas.model.developers.Backend;
import com.pas.pas.model.developers.FrontEnd;
import com.pas.pas.model.technologies.NodeJs;
import com.pas.pas.model.technologies.React;
import com.pas.pas.model.technologies.RubyOnRails;
import com.pas.pas.model.technologies.Technology;
import com.pas.pas.model.users.Admin;
import com.pas.pas.model.users.Client;
import com.pas.pas.model.users.ResourceAdministrator;
import com.pas.pas.repository.DeveloperRepository;
import com.pas.pas.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DbInit implements CommandLineRunner {
    private UserRepository userRepository;
    private DeveloperRepository developerRepository;
    private PasswordEncoder passwordEncoder;

    public DbInit(UserRepository userRepository, DeveloperRepository developerRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.developerRepository = developerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        userRepository.addUser(new Admin("Kamil", "Celejewski","ADMIN", UUID.randomUUID(), passwordEncoder.encode("123")));
        userRepository.addUser(new Client("Marcin1", "Morawski","CLIENT", UUID.randomUUID(),passwordEncoder.encode("123")));
        userRepository.addUser(new Client("Marcin2", "Morawski","CLIENT", UUID.randomUUID(),passwordEncoder.encode("123")));
        userRepository.addUser(new Client("Marcin3", "Morawski","CLIENT", UUID.randomUUID(),passwordEncoder.encode("123")));
        userRepository.addUser(new Client("Marcin4", "Morawski","CLIENT", UUID.randomUUID(),passwordEncoder.encode("123")));
        userRepository.addUser(new ResourceAdministrator("Szymon", "Dobrowolski","RESOURCE_ADMINISTRATOR", UUID.randomUUID(),passwordEncoder.encode("123")));

        Technology rubyOnRails = new RubyOnRails();
        Technology react = new React();
        Technology nodeJs = new NodeJs();
        developerRepository.addDeveloper(new Backend("Ernest","Kowalski", rubyOnRails, UUID.randomUUID()));
        developerRepository.addDeveloper(new FrontEnd("Wiktor","Kowalski", react, UUID.randomUUID()));
        developerRepository.addDeveloper(new Backend("Bartek","Kowalski", nodeJs, UUID.randomUUID()));
    }
}
