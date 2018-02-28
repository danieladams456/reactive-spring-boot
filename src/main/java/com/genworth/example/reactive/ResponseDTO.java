package com.genworth.example.reactive;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseDTO {
	String status;
	String message;
}
