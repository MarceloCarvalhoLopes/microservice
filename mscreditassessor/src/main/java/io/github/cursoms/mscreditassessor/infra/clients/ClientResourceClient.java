package io.github.cursoms.mscreditassessor.infra.clients;

import io.github.cursoms.mscreditassessor.domain.model.DataClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "msclients", path = "/clients")
public interface ClientResourceClient {

    @GetMapping(params = "cpf")
    ResponseEntity<DataClient> findByCpf(@RequestParam("cpf") String cpf);

}
