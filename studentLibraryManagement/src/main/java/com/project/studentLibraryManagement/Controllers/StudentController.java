package com.project.studentLibraryManagement.Controllers;

import com.project.studentLibraryManagement.RequestDto.StudentRequestDto;
import com.project.studentLibraryManagement.ResponseDto.StudentResponseDto;
import com.project.studentLibraryManagement.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/addStudent")
    public ResponseEntity<StudentResponseDto> addStudent(@RequestBody StudentRequestDto studentRequestDto) {
        StudentResponseDto studentResponseDto=studentService.addStudent(studentRequestDto);
//        return new ResponseEntity<>(studentResponseDto, HttpStatus.CREATED);
        return ResponseEntity.status(HttpStatus.CREATED).body(studentResponseDto);
    }
}
