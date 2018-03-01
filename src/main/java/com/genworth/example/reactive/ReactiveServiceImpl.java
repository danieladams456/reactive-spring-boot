package com.genworth.example.reactive;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Service
public class ReactiveServiceImpl implements ReactiveService {
	static WebClient traefikWebClient;
	static {
		traefikWebClient = WebClient.builder().baseUrl("http://ad0163ably.genworth.net:8080").build();
	}
	
	@Override
	public Mono<SampleResponseDTO> asyncWeb() {
		return traefikWebClient.get().uri("/health")
				.retrieve().bodyToMono(TraefikHealthResponseDTO.class)
				.map(this::toApiResponse);
	}
	
	private SampleResponseDTO toApiResponse(TraefikHealthResponseDTO res) {
		return SampleResponseDTO.builder().status("ok").message(res.average_response_time).build();
	}
}
