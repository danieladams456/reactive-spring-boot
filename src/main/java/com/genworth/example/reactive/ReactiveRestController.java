package com.genworth.example.reactive;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class ReactiveRestController {
	
	@GetMapping
	public ResponseDTO test() {
		return ResponseDTO.builder()
				.status("ok")
				.message("success!!!!!!!!")
				.build();
	}
}
