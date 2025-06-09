package com.project.studentLibraryManagement.Transformers;

import com.project.studentLibraryManagement.ResponseDto.DeleteResponse;
import org.springframework.http.HttpStatus;

public class DeleteResponseTransformer {
    public static DeleteResponse cardDeleteResponse(){
        return DeleteResponse.builder()
                .isSuccess(true)
                .httpStatus(HttpStatus.OK)
                .build();
    }
}
