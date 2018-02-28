package com.genworth.example.reactive;

import reactor.core.publisher.Mono;

public interface ReactiveService {
	public Mono<TraefikHealthResponseDTO> asyncWeb();
}
