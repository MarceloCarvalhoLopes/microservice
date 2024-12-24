package io.github.cursoms.msclients.resources;

import io.github.cursoms.msclients.Services.ClientService;
import io.github.cursoms.msclients.domain.Client;
import io.github.cursoms.msclients.representation.ClientSaveRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
@Slf4j
public class ClientResource {

        private final ClientService service;

    @GetMapping
    public String status(){
        log.info("Getting status of client microservice");
        return "ok";
    }

    @PostMapping
    public ResponseEntity save(@RequestBody ClientSaveRequest request){
        Client client = request.toModel();
        service.save(client);
        URI headerLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("cpf={cpf}")
                .buildAndExpand(client.getCpf())
                .toUri();
        return ResponseEntity.created(headerLocation).build();
    }

    @GetMapping(params = "cpf")
    public ResponseEntity findByCpf(@RequestParam("cpf") String cpf){
        var client = service.getByCpf(cpf);

        if(client.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(client);

    }


}
