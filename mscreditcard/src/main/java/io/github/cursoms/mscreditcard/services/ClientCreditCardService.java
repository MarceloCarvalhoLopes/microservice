package io.github.cursoms.mscreditcard.services;

import io.github.cursoms.mscreditcard.domain.ClientCreditCard;
import io.github.cursoms.mscreditcard.repositories.ClientCreditCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientCreditCardService {

    private final ClientCreditCardRepository repository;

    public List<ClientCreditCard> listCreditCardByCpf(String cpf){
        return repository.findByCpf(cpf);
    }

}
