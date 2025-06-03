package com.project.studentLibraryManagement.Transformers;

import com.project.studentLibraryManagement.Models.Address;
import com.project.studentLibraryManagement.RequestDto.AuthorRequestDto;
import com.project.studentLibraryManagement.RequestDto.StudentRequestDto;

public class AddressTransformer {
    public static Address createAddressFromRequestDto(StudentRequestDto studentRequestDto) {
        return Address.builder()
                .country(studentRequestDto.getCountry())
                .state(studentRequestDto.getState())
                .city(studentRequestDto.getCity())
                .locality(studentRequestDto.getLocality())
                .area(studentRequestDto.getArea())
                .pincode(studentRequestDto.getPincode())
                .build();
    }
    public static Address createAddressFromRequestDto(AuthorRequestDto authorRequestDto) {
        return Address.builder()
                .country(authorRequestDto.getCountry())
                .state(authorRequestDto.getState())
                .city(authorRequestDto.getCity())
                .locality(authorRequestDto.getLocality())
                .area(authorRequestDto.getArea())
                .pincode(authorRequestDto.getPincode())
                .build();
    }
}
