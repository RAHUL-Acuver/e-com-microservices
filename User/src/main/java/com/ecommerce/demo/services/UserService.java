package com.ecommerce.demo.services;

import com.ecommerce.demo.dto.UserRequest;
import com.ecommerce.demo.dto.UserResponse;
import com.ecommerce.demo.models.Address;
import com.ecommerce.demo.models.User;
import com.ecommerce.demo.repository.UserRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@Service
public class UserService {

    private final UserRepository userRepository;

    private Long id=1L;

    List<User> users = new ArrayList<User>();

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUser(UserRequest userRequest) {
        User user = new User();
        updateUserRequest(user,userRequest);
        userRepository.save(user);
    }

    private void updateUserRequest(User user, UserRequest userRequest) {
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setPhoneNumber(userRequest.getPhoneNumber());
        if(userRequest.getAddress()!=null){
            Address address = new Address();
            address.setStreet(userRequest.getAddress().getStreet());
            address.setCity(userRequest.getAddress().getCity());
            address.setState(userRequest.getAddress().getState());
            address.setCountry(userRequest.getAddress().getCountry());
            user.setAddress(address);
        }

    }

    public List<UserResponse> fetchAllUsers() {
        return userRepository.findAll()
                .stream().map(this::mapToUserResponse).toList();


    }

    public Optional<UserResponse> fetchUserById(Long id) {
//        for (User user : users) {
//            if (user.getId().equals(id)) {
//                return user;
//            }
//        }
//        return null;

        return userRepository.findById(id)
                .map(this::mapToUserResponse);
    }

    public boolean updateUserById(Long id,UserRequest updatedUserRequest) {
        return userRepository.findById(id)
                .map(user -> {
                   updateUserRequest(user,updatedUserRequest);
                    userRepository.save(user);
                    return true;
                }).orElse(false);
    }

    public UserResponse mapToUserResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setEmail(user.getEmail());
        userResponse.setPhoneNumber(user.getPhoneNumber());
        userResponse.setRole(String.valueOf(user.getRole()));

        if(user.getAddress() != null){
            Address address = new Address();
            address.setStreet(user.getAddress().getStreet());
            address.setCity(user.getAddress().getCity());
            address.setState(user.getAddress().getState());
//            addressDTO.setZipcode(user.getAddress().getZipcode());
            address.setCountry(user.getAddress().getCountry());
            userResponse.setAddress(address);
        }

        return userResponse;
    }

}
