package io.github.cursoms.mscreditassessor.services.excptions;


import lombok.Getter;

public class ErrorComunicationMicroserviceException extends Exception {

    @Getter
    private Integer status;

    public ErrorComunicationMicroserviceException(String message, Integer status) {
        super(message);
        this.status = status;
    }
}
