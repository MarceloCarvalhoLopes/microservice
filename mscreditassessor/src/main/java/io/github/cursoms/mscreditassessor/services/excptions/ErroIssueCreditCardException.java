package io.github.cursoms.mscreditassessor.services.excptions;

public class ErroIssueCreditCardException extends RuntimeException{
    public ErroIssueCreditCardException(String message) {
        super(message);
    }
}
