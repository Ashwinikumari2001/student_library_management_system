package com.project.studentLibraryManagement.Controllers;

import com.project.studentLibraryManagement.RequestDto.StudentRequestDto;
import com.project.studentLibraryManagement.ResponseDto.StudentDeleteDto;
import com.project.studentLibraryManagement.ResponseDto.StudentResponseDto;
import com.project.studentLibraryManagement.ResponseDto.StudentResponseDto2;
import com.project.studentLibraryManagement.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/getStudentByEmail")
    public ResponseEntity<StudentResponseDto2> getStudentByEmail(@RequestParam String studentEmail){
        StudentResponseDto2 studentResponseDto2=studentService.getStudentByEmail(studentEmail);
        return ResponseEntity.status(HttpStatus.OK).body(studentResponseDto2);
    }

    @GetMapping("/getAllStudents")
    public ResponseEntity<List<StudentResponseDto2>> getAllStudents(){
        List<StudentResponseDto2> studentResponseDto2=studentService.getAllStudents();
        return ResponseEntity.status(HttpStatus.OK).body(studentResponseDto2);
    }

    @PutMapping("/updateStudent/{studentId}")
    public ResponseEntity<StudentResponseDto> updateStudent(@RequestBody StudentRequestDto studentRequestDto,
                                                            @PathVariable int studentId){
        StudentResponseDto studentResponseDto=studentService.updateStudent(studentRequestDto,studentId);
        return ResponseEntity.status(HttpStatus.OK).body(studentResponseDto);
    }

    @DeleteMapping("/deleteStudent/{studentId}")
    public ResponseEntity<StudentDeleteDto> deleteStudent(@PathVariable int studentId){
        StudentDeleteDto studentDeleteDto=studentService.deleteStudent(studentId);
        return ResponseEntity.status(HttpStatus.OK).body(studentDeleteDto);
    }

}
