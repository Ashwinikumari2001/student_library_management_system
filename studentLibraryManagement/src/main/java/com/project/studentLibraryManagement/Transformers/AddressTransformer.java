package com.project.studentLibraryManagement.Transformers;

import com.project.studentLibraryManagement.Models.Address;
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
}
