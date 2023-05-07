package com.vandai.Auth.exception;

import com.vandai.Auth.dto.response.MessageResponse;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignUpException {
    private MessageResponse messageResponse;

}
