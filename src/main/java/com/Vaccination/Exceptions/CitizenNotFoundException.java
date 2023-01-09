package com.Vaccination.Exceptions;

public class CitizenNotFoundException extends RuntimeException {
    public CitizenNotFoundException(Long id) {
        super("Could not find citizen " + id);
    }
}
