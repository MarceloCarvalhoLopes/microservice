package io.github.cursoms.msclients.resources;

import io.github.cursoms.msclients.Services.ClientService;
import io.github.cursoms.msclients.domain.Client;
import io.github.cursoms.msclients.reprosentations.ClientSaveRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientResource {

    private final ClientService service;

    @GetMapping
    public String status(){
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
        Optional<Client> client = service.getByCpf(cpf);

        if(client.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(client);

    }


}
