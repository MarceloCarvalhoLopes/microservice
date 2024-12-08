package io.github.cursoms.mscreditassessor.services.excptions;

public class DataClientNotFoundException extends Exception{
    public DataClientNotFoundException(){
        super("Dados de clientes não encontrado para o cpf informado!");
    }
}
