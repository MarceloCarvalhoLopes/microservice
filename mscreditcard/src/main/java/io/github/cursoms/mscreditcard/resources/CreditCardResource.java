package io.github.cursoms.mscreditcard.resources;

import io.github.cursoms.mscreditcard.domain.ClientCreditCard;
import io.github.cursoms.mscreditcard.domain.CreditCard;
import io.github.cursoms.mscreditcard.resources.representation.CreditCardByClientResponse;
import io.github.cursoms.mscreditcard.resources.representation.CreditCardSaveRequest;
import io.github.cursoms.mscreditcard.services.ClientCreditCardService;
import io.github.cursoms.mscreditcard.services.CreditCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("creditcards")
@RequiredArgsConstructor
public class CreditCardResource {

    private final CreditCardService creditCardService;
    private final ClientCreditCardService clientCreditCardService;

    @GetMapping
    public String status(){
        return "ok";
    }

    @PostMapping
    public ResponseEntity save(@RequestBody CreditCardSaveRequest request){
        CreditCard creditCard = request.toModel();
        creditCardService.save(creditCard);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(params = "income")
    public ResponseEntity<List<CreditCard>> getCreditCardIncomeUntil(@RequestParam("income") Long income){
        List<CreditCard> list = creditCardService.getCreditCardIncomeLessEqual(income);
        return ResponseEntity.ok(list);
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<List<CreditCardByClientResponse>> findByCpf(
           @RequestParam("cpf") String cpf){
       List<ClientCreditCard> list = clientCreditCardService.listCreditCardByCpf(cpf);
       List<CreditCardByClientResponse> resultList =  list.stream()
               .map(CreditCardByClientResponse::fromModel)
               .collect(Collectors.toList());
            return ResponseEntity.ok(resultList);

    }

}
