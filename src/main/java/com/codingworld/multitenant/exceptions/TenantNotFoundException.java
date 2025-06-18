package com.codingworld.multitenant.exceptions;

public class TenantNotFoundException extends AppException {
    public TenantNotFoundException() {
        super("X-TenantID não está presente no Header", 400);
    }
}
