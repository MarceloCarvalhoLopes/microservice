package io.github.cursoms.msclients.reprosentations;

import io.github.cursoms.msclients.domain.Client;
import lombok.Data;

@Data
public class ClientSaveRequest {
    private String cpf;
    private String name;
    private Integer age;

    public Client toModel(){
        return new Client(cpf,name,age);
    }

}
