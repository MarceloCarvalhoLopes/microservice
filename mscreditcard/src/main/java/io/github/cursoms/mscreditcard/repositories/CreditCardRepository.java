package io.github.cursoms.mscreditcard.repositories;

import io.github.cursoms.mscreditcard.domain.model.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface CreditCardRepository extends JpaRepository<CreditCard,Long> {
    List<CreditCard> findByIncomeLessThanEqual(BigDecimal incomeBigDecimal);
}
