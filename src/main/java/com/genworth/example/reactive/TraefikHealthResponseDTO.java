package com.genworth.example.reactive;

import lombok.Data;

@Data
public class TraefikHealthResponseDTO {
	int pid;
	String uptime;
	double uptime_sec;
	String time;
	int unixtime;
	int count;
	int total_count;
	String total_response_time;
	int total_response_time_sec;
	String average_response_time;
	int average_response_time_sec;
}
