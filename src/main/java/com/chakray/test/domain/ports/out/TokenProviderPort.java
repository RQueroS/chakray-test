package com.chakray.test.domain.ports.out;

import com.chakray.test.domain.TokenPayload;

public interface TokenProviderPort {
    String generateToken(TokenPayload payload);

    boolean validateToken(String token);

    TokenPayload getPayloadFromToken(String token);
}
