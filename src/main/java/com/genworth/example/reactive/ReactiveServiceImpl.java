package com.genworth.example.reactive;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Service
public class ReactiveServiceImpl implements ReactiveService {
	static WebClient traefikWebClient;
	static WebClient whoamiWebClient;
	static {
		traefikWebClient = WebClient.builder().baseUrl("http://localhost:8082").build();
		whoamiWebClient = WebClient.builder().baseUrl("http://localhost:8081").build();
	}
	
	@Override
	public Mono<SampleResponseDTO> webAsync() {
		return traefikWebClient.get().uri("/health")
				.retrieve().bodyToMono(TraefikHealthResponseDTO.class)
				.map(this::traefikToApiResponse);
	}

	@Override
	public Mono<SampleResponseDTO> webAsync2(String path) {
		return whoamiWebClient.get().uri(path)
				.retrieve().bodyToMono(String.class)
				.map(body -> SampleResponseDTO.builder().status("ok").message(body).build());
	}
	
	private SampleResponseDTO traefikToApiResponse(TraefikHealthResponseDTO in) {
		return SampleResponseDTO.builder().status("ok").message(in.uptime).build();
	}

	@Override
	public Mono<SampleResponseDTO> webAsyncSequential() {
		return traefikWebClient.get().uri("/health")
				.retrieve().bodyToMono(TraefikHealthResponseDTO.class)
				.flatMap(intermediate -> whoamiWebClient.get().uri(intermediate.uptime).retrieve().bodyToMono(String.class))
				.map(body -> SampleResponseDTO.builder().status("ok").message(body).build());
	}
	
	public Mono<SampleResponseDTO> webAsyncParallel(String path) {
		return Mono.zip(webAsync(), webAsync2(path))
				.map(tuple -> SampleResponseDTO.builder().status("ok")
						.message(tuple.getT1().message).messageTwo(tuple.getT2().message)
						.build());
	}
}
