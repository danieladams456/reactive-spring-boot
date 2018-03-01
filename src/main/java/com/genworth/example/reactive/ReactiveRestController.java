package com.genworth.example.reactive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@CrossOrigin
@RestController
public class ReactiveRestController {
	@Autowired
	private ReactiveService reactiveService;
	
	@GetMapping("static/sync")
	public SampleResponseDTO staticSync() {
		return SampleResponseDTO.builder()
				.status("ok")
				.message("success!!!!!!!!")
				.build();
	}
	
	@GetMapping("static/async")
	public Mono<SampleResponseDTO> staticAsync() {
		return Mono.just(SampleResponseDTO.builder()
				.status("ok")
				.message("success!!!!!!!!")
				.build());
	}
	
	@GetMapping("web/async")
	public Mono<SampleResponseDTO> webAsync() {
		return this.reactiveService.asyncWeb();
	}
}
