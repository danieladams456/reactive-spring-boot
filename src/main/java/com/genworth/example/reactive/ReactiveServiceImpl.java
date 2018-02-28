package com.genworth.example.reactive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

public class ReactiveServiceImpl implements ReactiveService {

	@Autowired
	WebClient webClient;
	
	@Override
	public Mono<TraefikHealthResponseDTO> asyncWeb() {
		return webClient.get().uri("http://ad0163ably.genworth.net:8080/health")
				.retrieve().bodyToMono(TraefikHealthResponseDTO.class);
	}
}
