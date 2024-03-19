package sda.jre28.travelagency.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sda.jre28.travelagency.exceptions.UserAlreadyExistsException;
import sda.jre28.travelagency.model.Role;
import sda.jre28.travelagency.model.User;
import sda.jre28.travelagency.dto.SignUpDto;
import sda.jre28.travelagency.repository.RoleRepository;
import sda.jre28.travelagency.repository.UserRepository;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(String name, String email, String username, String rawPassword) throws UserAlreadyExistsException {
        if (userRepository.existsByUsername(username)) {
            throw new UserAlreadyExistsException("Username is already taken.");
        }
        if (userRepository.existsByEmail(email)) {
            throw new UserAlreadyExistsException("Email is already in use.");
        }
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(rawPassword)); // Encode the password

        // Fetch the ROLE_USER from the database
        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));

        // Set the roles
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);
        user.setRoles(roles);

        // Commented code below is bad. It makes the Set<Role> roles an immutable set, causing UnsupportedOperationException
        // user.setRoles(Collections.singleton(userRole));

        return userRepository.save(user);
    }

    public User registerNewUser(SignUpDto signUpDto) throws UserAlreadyExistsException {
        if (userRepository.existsByUsername(signUpDto.getUsername())) {
            throw new UserAlreadyExistsException("Username is already taken!");
        }

        if (userRepository.existsByEmail(signUpDto.getEmail())) {
            throw new UserAlreadyExistsException("Email is already taken!");
        }

        User newUser = createUser(signUpDto.getName(),
                signUpDto.getEmail(),
                signUpDto.getUsername(),
                signUpDto.getPassword());

        return userRepository.save(newUser);
    }

    public User findById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }
}
