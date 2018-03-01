package com.genworth.example.reactive;

import java.net.URI;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Service
public class ReactiveServiceImpl implements ReactiveService {
	static WebClient traefikWebClient;
	static WebClient whoamiWebClient;
	static {
		traefikWebClient = WebClient.builder().baseUrl("http://ad0163ably.genworth.net:8080").build();
		whoamiWebClient = WebClient.builder().baseUrl("http://localhost:8081").build();
	}
	
	@Override
	public Mono<SampleResponseDTO> webAsync() {
		return traefikWebClient.get().uri("/health")
				.retrieve().bodyToMono(TraefikHealthResponseDTO.class)
				.map(res -> SampleResponseDTO.builder().status("ok").message(res.average_response_time).build());
	}

	@Override
	public Mono<SampleResponseDTO> webAsync2(String path) {
		return whoamiWebClient.get().uri(path)
				.retrieve().bodyToMono(String.class)
				.map(body -> SampleResponseDTO.builder().status("ok").message(body).build());
	}

	@Override
	public Mono<SampleResponseDTO> webAsyncSequential() {
		return traefikWebClient.get().uri("/health")
				.retrieve().bodyToMono(TraefikHealthResponseDTO.class)
				.flatMap(intermediate -> whoamiWebClient.get().uri(intermediate.average_response_time).retrieve().bodyToMono(String.class))
				.map(body -> SampleResponseDTO.builder().status("ok").message(body).build());
	}
	
	
	
}
