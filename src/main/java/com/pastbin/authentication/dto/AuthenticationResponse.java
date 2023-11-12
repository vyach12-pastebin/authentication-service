package com.pastbin.authentication.dto;

import lombok.Builder;

@Builder
public record AuthenticationResponse(String accessToken) {}
