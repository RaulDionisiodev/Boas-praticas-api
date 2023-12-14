package br.com.alura.adopet.api.exception;

public class PetNotFoundException extends RuntimeException{

    public PetNotFoundException(String message) {
        super(message);
    }
}
