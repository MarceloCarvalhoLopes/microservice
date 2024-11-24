package io.github.cursoms.mscreditcard.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("creditcards")
public class CreditCardResource {

    @GetMapping
    public String status(){
        return "ok";
    }
}
