package com.genworth.example.reactive;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
@JsonInclude(Include.NON_NULL)
public class SampleResponseDTO {
	String status;
	String message;
	String messageTwo;
}
