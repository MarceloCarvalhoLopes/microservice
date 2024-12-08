package io.github.cursoms.mscreditassessor.resources;

import io.github.cursoms.mscreditassessor.domain.StatusClient;
import io.github.cursoms.mscreditassessor.services.CreditAssessorService;
import io.github.cursoms.mscreditassessor.services.excptions.DataClientNotFoundException;
import io.github.cursoms.mscreditassessor.services.excptions.ErrorComunicationMicroserviceException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity searchStatusClient(@RequestParam("cpf") String cpf){
        try {
            StatusClient statusClient = assessorService.getStatusClient(cpf);
            return ResponseEntity.ok(statusClient);
        } catch (DataClientNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (ErrorComunicationMicroserviceException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }

    }
}
