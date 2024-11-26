package io.github.cursoms.mscreditcard.services;

import io.github.cursoms.mscreditcard.domain.CreditCard;
import io.github.cursoms.mscreditcard.repositories.CreditCardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CreditCardService {

    private final CreditCardRepository repository;

    @Transactional
    public CreditCard save(CreditCard creditCard){
        return repository.save(creditCard);
    }

    public List<CreditCard> getCreditCardIncomeLessEqual(Long income){
        var incomeBigDecimal = BigDecimal.valueOf(income);
        return repository.findByIncomeLessThanEqual(incomeBigDecimal);
    }


}
