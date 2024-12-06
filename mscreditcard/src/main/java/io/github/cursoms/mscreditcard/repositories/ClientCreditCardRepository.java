package io.github.cursoms.mscreditcard.repositories;

import io.github.cursoms.mscreditcard.domain.ClientCreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientCreditCardRepository extends JpaRepository<ClientCreditCard,Long> {

    List<ClientCreditCard> findByCpf(String cpf);
}
