package com.web.apigateway.Controller;


import com.web.apigateway.Entity.Cliente;
import com.web.apigateway.Entity.Compra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
public class CompraController {

    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private ClienteController clienteController;

    @GetMapping(value="/api/compra")
    public ResponseEntity<List<Compra>> listarCompras() {

        ResponseEntity<List<Compra>> listResponseEntity = restTemplate.exchange("http://localhost:8085/compras",
                HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Compra>>() {});

        return new ResponseEntity<>(listResponseEntity.getBody(), listResponseEntity.getStatusCode());
    }


    @PostMapping("/api/compra")
    public ResponseEntity<Compra> crearCompra(@RequestBody Compra compra){

        HttpEntity<Compra> request = new HttpEntity<>(compra);
        ResponseEntity<Compra> exchange = restTemplate.exchange("http://localhost:8085/compras",
                HttpMethod.POST,
                request, Compra.class);

        return new ResponseEntity<>(exchange.getStatusCode());
    }



    @GetMapping(value = "api/compras/paginacion",  params = {"limit", "offset"})
    public ResponseEntity<List<Compra>> comprasPorPaginacion(@RequestParam("limit") int limit, @RequestParam("offset") int offset) {

        String url = "http://localhost:8085/compras/paginacion";
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url)
                .queryParam("limit", limit)
                .queryParam("offset", offset);

        ResponseEntity<List<Compra>> lista = restTemplate.exchange(builder.toUriString(),
                HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Compra>>(){});

        return new ResponseEntity<>(lista.getBody(), lista.getStatusCode());
    }


    @GetMapping(value = "api/compras/paginacion/cliente/{cliente}",  params = {"limit", "offset"})
    public ResponseEntity<List<Compra>> comprasPorPaginacionCliente(@RequestParam("limit") int limit, @RequestParam("offset") int offset, @PathVariable Integer cliente) {

        Cliente c2 = clienteController.buscarPorId(cliente).getBody();
        HttpEntity<Cliente> request = new HttpEntity<>(c2);

        String url = "http://localhost:8085/compras/paginacion";
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url)
                .queryParam("limit", limit)
                .queryParam("offset", offset);

        ResponseEntity<List<Compra>> lista = restTemplate.exchange(builder.toUriString(),
                HttpMethod.GET, request ,
                new ParameterizedTypeReference<List<Compra>>(){});

        return new ResponseEntity<>(lista.getBody(), lista.getStatusCode());
    }


    @GetMapping("/api/compras/{id}")
    public ResponseEntity<Compra> compraPorId(@PathVariable int id) {

        HttpEntity<Integer> request = new HttpEntity<>(id);
        ResponseEntity<Compra> exchange = restTemplate.exchange("http://localhost:8085/compras/" + id,
                HttpMethod.GET,
                request, Compra.class);

        return new ResponseEntity<>(exchange.getBody(), exchange.getStatusCode());

    }


}