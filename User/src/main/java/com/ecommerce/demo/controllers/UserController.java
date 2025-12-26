package com.ecommerce.demo.controllers;

import com.ecommerce.demo.dto.UserRequest;
import com.ecommerce.demo.dto.UserResponse;
import com.ecommerce.demo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users/api")
public class UserController {


    private final UserService userService;

    @GetMapping("/get")
    public List<UserResponse> getUsers() {
        return userService.fetchAllUsers();
    }

//    @GetMapping("/get/{id}")
//    public ResponseEntity<User> getUser(@PathVariable Long id) {
//        return new ResponseEntity<>(userService.fetchUserById(id),HttpStatus.OK);
//    }

    @GetMapping("/get/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id) {
       return userService.fetchUserById(id)
               .map(ResponseEntity::ok)
               .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody UserRequest updatedUserRequest) {
         boolean updated=userService.updateUserById(id,updatedUserRequest);
                if(updated)
                    return ResponseEntity.ok().build();
                return ResponseEntity.notFound().build();
    }

    @PostMapping("/save")
    public String addUser(@RequestBody UserRequest userRequest) {

        userService.addUser(userRequest);
        return "User added successfully";

    }
}
