package com.project.studentLibraryManagement.Services;

import com.project.studentLibraryManagement.Models.User;
import com.project.studentLibraryManagement.Repository.UserRepository;
import com.project.studentLibraryManagement.RequestDto.LoginRequest;
import com.project.studentLibraryManagement.RequestDto.UserRequestDto;
import com.project.studentLibraryManagement.ResponseDto.UserResponseDto;
import com.project.studentLibraryManagement.ResponseDto.UserResponseDto2;
import com.project.studentLibraryManagement.Transformers.UserTransformer;
import com.project.studentLibraryManagement.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    public UserResponseDto addUser(UserRequestDto userRequestDto) {
        Optional<User> userOptional=userRepository.findByEmail(userRequestDto.getEmail());
        if(userOptional.isPresent()){
            throw new RuntimeException("User already exist, please login !!!");
        }
        User user= UserTransformer.createUserFromUserRequestDto(userRequestDto);
        User savedUser=userRepository.save(user);
        return UserTransformer.createUserResponseDto(savedUser);
    }
    public UserResponseDto2 getUserById(int userId){
        User user=userRepository.findById(userId).orElseThrow(()->new RuntimeException("user not found with given userId!!"));
        return UserTransformer.createUserResponseDto2(user);

    }
    public UserResponseDto2 updateUser(UserRequestDto userRequestDto){
        User user=userRepository.findByEmail(userRequestDto.getEmail()).orElseThrow(()->new RuntimeException("user not found with given email and password!!"));
        user.setName(userRequestDto.getName());
        user.setPassword(userRequestDto.getPassword());
        User savedUser=userRepository.save(user);
        return UserTransformer.createUserResponseDto2(savedUser);
    }
    public UserResponseDto deleteUser(int userId){
        User user=userRepository.findById(userId).orElseThrow(()->new RuntimeException("not found"));
        userRepository.deleteById(userId);
        return UserTransformer.createUserResponseDto(user);
    }
    public UserResponseDto searchUser(LoginRequest loginRequest){
        String email=loginRequest.getEmail();
        String password=loginRequest.getPassword();
        User user= userRepository.findByEmailAndPassword(email,password).orElseThrow(()->new RuntimeException("user not found !!"));
        if(user==null){
            throw new UserNotFoundException("Invalid email or password");
        }
        return UserTransformer.createUserResponseDto(user);
    }
}
