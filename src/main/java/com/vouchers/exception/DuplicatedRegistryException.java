package com.vouchers.exception;

public class DuplicatedRegistryException extends RuntimeException {
    public DuplicatedRegistryException(String message) {
        super(message);
    }
}
