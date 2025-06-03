package com.project.studentLibraryManagement.Services;

import com.project.studentLibraryManagement.Enums.Gender;
import com.project.studentLibraryManagement.Models.Address;
import com.project.studentLibraryManagement.Models.Card;
import com.project.studentLibraryManagement.Models.Student;
import com.project.studentLibraryManagement.Repository.StudentRepository;
import com.project.studentLibraryManagement.RequestDto.StudentRequestDto;
import com.project.studentLibraryManagement.ResponseDto.StudentDeleteDto;
import com.project.studentLibraryManagement.ResponseDto.StudentResponseDto;
import com.project.studentLibraryManagement.ResponseDto.StudentResponseDto2;
import com.project.studentLibraryManagement.Transformers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    public StudentResponseDto addStudent(StudentRequestDto studentRequestDto) {
        Student student= StudentTransformer.createStudentFromRequestDto(studentRequestDto);
        Address address= AddressTransformer.createAddressFromRequestDto(studentRequestDto);

        student.setAddress(address);
        address.setStudent(student);
        studentRepository.save(student);

        return StudentResponseTransformer.createResponseDtoFromStudent(studentRequestDto);
    }

    public StudentResponseDto2 getStudentByEmail(String studentEmail){
        Student student=studentRepository.findByEmail(studentEmail).orElseThrow();
        Card card=student.getCard();
        if(card==null){
            throw new RuntimeException("Card doesn't exist for student: "+student.getEmail());
        }
        return StudentResponseTransformer2.createResponse(student,card);
    }
    public List<StudentResponseDto2> getAllStudents(){
        List<Student> students=studentRepository.findAll();
        List<StudentResponseDto2> studentResponseDto2List=new ArrayList<>();
        for(Student student:students){
            Card card=student.getCard();
            if(card!=null){
                StudentResponseDto2 studentResponseDto2=StudentResponseTransformer2.createResponse(student,card);
                studentResponseDto2List.add(studentResponseDto2);
            }
        }
        return studentResponseDto2List;
    }

    public StudentResponseDto updateStudent(StudentRequestDto studentRequestDto, int studentId){
        Student student=studentRepository.findById(studentId).orElseThrow();
        Address address=student.getAddress();

        student.setName(studentRequestDto.getName());
        student.setMobile(studentRequestDto.getMobile());
        student.setDepartment(studentRequestDto.getDepartment());
        student.setSemester(studentRequestDto.getSemester());
        student.setGender(studentRequestDto.getGender());
        student.setDateOfBirth(studentRequestDto.getDateOfBirth());
        address.setCountry(studentRequestDto.getCountry());
        address.setState(studentRequestDto.getState());
        address.setCity(studentRequestDto.getCity());
        address.setLocality(studentRequestDto.getLocality());
        address.setArea(studentRequestDto.getArea());
        address.setPincode(studentRequestDto.getPincode());

        student.setAddress(address);
        studentRepository.save(student);

        return StudentResponseTransformer.createResponseDtoFromStudent(studentRequestDto);
    }

    public StudentDeleteDto deleteStudent(int studentId) {
        Student student=studentRepository.findById(studentId).orElseThrow();
        studentRepository.deleteById(studentId);
        return StudentDeleteTransformer.studentDeleteDto(student);
    }
}
