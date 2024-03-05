package sda.jre28.travelagency.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sda.jre28.travelagency.model.Tour;
import sda.jre28.travelagency.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v3")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/mytours")
    public List<Tour> findAllBoughtTours(@RequestParam("userId") Long userId) {
        return userService.findAllBoughtTours(userId);
    }
}
