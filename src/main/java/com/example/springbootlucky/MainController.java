package com.example.springbootlucky;

import java.util.Random;
import java.util.Arrays;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller // This means that this class is a Controller
@RequestMapping(path = "/lottery") // This means URL's start with /lottery (after Application path)
public class MainController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${user-service.url}")
    private String userServiceBaseUrl;

    @GetMapping(path = "/winner")
    public @ResponseBody User getWinner() {
        String userResourceUrl = userServiceBaseUrl + "demo/all";
        // Error handling and null check is missing in this example
        User[] users = restTemplate.getForObject(userResourceUrl, User[].class);
        int randomWinner = new Random().nextInt(users.length);
        User winner = users[randomWinner];
        return winner;
    }
}