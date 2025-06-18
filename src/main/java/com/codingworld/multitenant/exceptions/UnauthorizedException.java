package com.codingworld.multitenant.exceptions;

public class UnauthorizedException extends AppException {
    public UnauthorizedException() {
        super("Usuário sem permissão", 401);
    }
}
