package com.genworth.example.reactive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

/**
 * Demo requirements:
 * docker run -d -p 8081:80 emilevauge/whoami
 * docker run -d -p 8082:8080 traefik --api
 */

@CrossOrigin
@RestController
public class ReactiveRestController {
	@Autowired
	private ReactiveService reactiveService;
	
	// static responses
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

	// single web request
	@GetMapping("web/async")
	public Mono<SampleResponseDTO> webAsync() {
		return this.reactiveService.webAsync();
	}
	@GetMapping("web/async2/{path}")
	public Mono<SampleResponseDTO> webAsync2(@PathVariable("path") String path) {
		return this.reactiveService.webAsync2(path);
	}
	
	// two web requests together
	@GetMapping("web/async/sequential")
	public Mono<SampleResponseDTO> webAsyncSequential() {
		return this.reactiveService.webAsyncSequential();
	}
	@GetMapping("web/async/parallel/{path}")
	public Mono<SampleResponseDTO> webAsyncParallel(@PathVariable("path") String path) {
		return this.reactiveService.webAsyncParallel(path);
	}
}
