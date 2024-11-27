package io.github.cursoms.mscreditcard.resources;

import io.github.cursoms.mscreditcard.domain.CreditCard;
import io.github.cursoms.mscreditcard.resources.representation.CreditCardSaveRequest;
import io.github.cursoms.mscreditcard.services.CreditCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("creditcards")
@RequiredArgsConstructor
public class CreditCardResource {


    private final CreditCardService service;

    @GetMapping
    public String status(){
        return "ok";
    }

    @PostMapping
    public ResponseEntity save(@RequestBody CreditCardSaveRequest request){
        CreditCard creditCard = request.toModel();
        service.save(creditCard);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(params = "income")
    public ResponseEntity<List<CreditCard>> getCreditCardIncomeUntil(@RequestParam("income") Long income){
        List<CreditCard> list = service.getCreditCardIncomeLessEqual(income);
        return ResponseEntity.ok(list);
    }

}
