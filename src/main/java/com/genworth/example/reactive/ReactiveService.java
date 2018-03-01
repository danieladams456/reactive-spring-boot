package com.genworth.example.reactive;

import reactor.core.publisher.Mono;

public interface ReactiveService {
	public Mono<SampleResponseDTO> webAsync();
	public Mono<SampleResponseDTO> webAsync2(String path);
	public Mono<SampleResponseDTO> webAsyncSequential();
}
