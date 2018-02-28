package com.genworth.example.reactive;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

@Service
public interface ReactiveService {
	public Mono<TraefikHealthResponseDTO> asyncWeb();
}
