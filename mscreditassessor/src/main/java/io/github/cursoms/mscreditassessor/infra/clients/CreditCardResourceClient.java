package io.github.cursoms.mscreditassessor.infra.clients;


import io.github.cursoms.mscreditassessor.domain.model.CreditCard;
import io.github.cursoms.mscreditassessor.domain.model.CreditCardClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "mscreditcard", path = "/creditcards")
public interface CreditCardResourceClient {

    @GetMapping(params = "cpf")
    ResponseEntity<List<CreditCardClient>> findByCpf(@RequestParam("cpf") String cpf);

    @GetMapping(params = "income")
    ResponseEntity<List<CreditCard>> getCreditCardIncomeUntil(@RequestParam("income") Long income);
}
