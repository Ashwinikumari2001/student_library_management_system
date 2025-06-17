package com.project.studentLibraryManagement.Controllers;

import com.project.studentLibraryManagement.RequestDto.LoginRequest;
import com.project.studentLibraryManagement.RequestDto.UserRequestDto;
import com.project.studentLibraryManagement.ResponseDto.UserResponseDto;
import com.project.studentLibraryManagement.ResponseDto.UserResponseDto2;
import com.project.studentLibraryManagement.Services.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    public ResponseEntity<UserResponseDto> addUser(@RequestBody UserRequestDto userRequestDto){
        UserResponseDto userResponseDto=userService.addUser(userRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponseDto);
    }
    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDto2> getUserById(@PathVariable int userId){
        UserResponseDto2 userResponseDto2=userService.getUserById(userId);
        return ResponseEntity.status(HttpStatus.OK).body(userResponseDto2);
    }
    @PutMapping("/{userId}")
    public ResponseEntity<UserResponseDto2> updateUser(@PathVariable int userId,@RequestBody UserRequestDto userRequestDto){
        UserResponseDto2 userResponseDto2=userService.updateUser(userId,userRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(userResponseDto2);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<UserResponseDto> deleteUser(@PathVariable int userId){
        UserResponseDto userResponseDto=userService.deleteUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body(userResponseDto);
    }

    @PostMapping("/findByEmailAndPassward")
    public ResponseEntity<UserResponseDto> findByEmailAndPassward(@RequestBody LoginRequest loginRequest){
        UserResponseDto userResponseDto=userService.searchUser(loginRequest);
        return ResponseEntity.status(HttpStatus.OK).body(userResponseDto);
    }

}
