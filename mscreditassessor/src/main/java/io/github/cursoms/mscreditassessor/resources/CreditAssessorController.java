package io.github.cursoms.mscreditassessor.resources;

import io.github.cursoms.mscreditassessor.domain.StatusClient;
import io.github.cursoms.mscreditassessor.services.CreditAssessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/creditassessor")
@RequiredArgsConstructor
public class CreditAssessorController {

    private final CreditAssessorService assessorService;

    @GetMapping
    public String status() {
        return "ok";
    }

    @GetMapping(value = "status-client", params = "cpf")
    public ResponseEntity<StatusClient> searchStatusClient(@RequestParam("cpf") String cpf){
        StatusClient statusClient = assessorService.getStatusClient(cpf);
        return ResponseEntity.ok(statusClient);
    }
}
