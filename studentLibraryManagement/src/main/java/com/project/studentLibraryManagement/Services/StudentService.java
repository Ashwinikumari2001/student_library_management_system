package com.project.studentLibraryManagement.Services;

import com.project.studentLibraryManagement.Models.Address;
import com.project.studentLibraryManagement.Models.Student;
import com.project.studentLibraryManagement.Repository.StudentRepository;
import com.project.studentLibraryManagement.RequestDto.StudentRequestDto;
import com.project.studentLibraryManagement.ResponseDto.StudentResponseDto;
import com.project.studentLibraryManagement.Transformers.AddressTransformer;
import com.project.studentLibraryManagement.Transformers.StudentResponseTransformer;
import com.project.studentLibraryManagement.Transformers.StudentTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

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
}
