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

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    public UserResponseDto addUser(UserRequestDto userRequestDto) {
        User user= UserTransformer.createUserFromUserRequestDto(userRequestDto);
        User savedUser=userRepository.save(user);
        return UserTransformer.createUserResponseDto(savedUser);
    }
    public UserResponseDto2 getUserById(int userId){
        User user=userRepository.findById(userId).orElseThrow(()->new RuntimeException("user not found with given userId!!"));
        return UserTransformer.createUserResponseDto2(user);

    }
    public UserResponseDto2 updateUser(int userId, UserRequestDto userRequestDto){
        User user=userRepository.findById(userId).orElseThrow(()->new RuntimeException("user not found with given userId!!"));
        user.setName(userRequestDto.getName());
        user.setEmail(userRequestDto.getEmail());
        user.setPassward(userRequestDto.getPassward());
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
        User user= userRepository.findByEmailAndPassward(email,password);
        if(user==null){
            throw new UserNotFoundException("Invalid email or password");
        }
        return UserTransformer.createUserResponseDto(user);
    }
}
