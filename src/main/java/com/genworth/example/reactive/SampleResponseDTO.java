package com.genworth.example.reactive;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SampleResponseDTO {
	String status;
	String message;
}
