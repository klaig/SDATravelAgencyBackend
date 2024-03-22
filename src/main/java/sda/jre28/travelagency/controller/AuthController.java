package sda.jre28.travelagency.controller;

import jakarta.validation.Valid;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sda.jre28.travelagency.dto.MessageResponse;
import sda.jre28.travelagency.exceptions.UserAlreadyExistsException;
import sda.jre28.travelagency.dto.LoginDto;
import sda.jre28.travelagency.model.Role;
import sda.jre28.travelagency.model.User;
import sda.jre28.travelagency.repository.RoleRepository;
import sda.jre28.travelagency.repository.UserRepository;
import sda.jre28.travelagency.dto.SignUpDto;
import sda.jre28.travelagency.service.CustomUserDetailsService;
import sda.jre28.travelagency.service.UserService;
import org.slf4j.Logger;
import sda.jre28.travelagency.util.JwtUtils;
import sda.jre28.travelagency.dto.JwtResponse;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtils jwtUtils;
    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> authenticateUser(@RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    loginDto.getUsernameOrEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        logger.debug("Authentication: {}", authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        Long id = user.getId();
        String email = user.getEmail();
        String role = user.getRoles().stream()
                .findFirst()
                .map(Role::getName)
                .orElse(null);
        String name = user.getName();
        return ResponseEntity.ok(new JwtResponse(jwt, "Bearer", id, username, role, email, name));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpDto signUpDto){
        try {
            userService.registerNewUser(signUpDto);
            return ResponseEntity.ok(new MessageResponse("User registered successfully"));
        } catch (UserAlreadyExistsException ex) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new MessageResponse(ex.getMessage()));
        }
    }
}
