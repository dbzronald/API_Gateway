package com.web.apigateway.Controller;

import com.web.apigateway.Entity.Cliente;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.List;

@RestController()
public class ClienteController {

    private RestTemplate restTemplate = new RestTemplate();

    @GetMapping(value = "/api/clientes/listado", produces = { "application/json" })
    public ResponseEntity<List<Cliente>> listar() {

        ResponseEntity<List<Cliente>> listResponseEntity = restTemplate.exchange(
                "http://localhost:8082/clientes",
                HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Cliente>>() {});

        System.out.println("Code:" + listResponseEntity.getStatusCode());
        return new ResponseEntity<>(listResponseEntity.getBody(), HttpStatus.OK);
    }


    @GetMapping(value = "/api/clientes/nombre/{nombre}")
    public ResponseEntity<List<Cliente>> clientesPorNombre(@PathVariable String nombre) {

        ResponseEntity<List<Cliente>> listResponseEntity = restTemplate.exchange(
                "http://localhost:8082/clientes/nombre/" + nombre,
                HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Cliente>>() {});

        return new ResponseEntity<>(listResponseEntity.getBody(), HttpStatus.OK);
    }


    @PostMapping(value = "/api/clientes", consumes = "application/json")
    public ResponseEntity<Cliente> crear(@RequestBody Cliente cliente) {

        HttpEntity<Cliente> request = new HttpEntity<>(cliente);
        ResponseEntity<Cliente> exchange = restTemplate.exchange("http://localhost:8082/clientes/",
                HttpMethod.POST,
                request, Cliente.class);

        return new ResponseEntity<>(exchange.getStatusCode());
    }


    @RequestMapping(value = "/api/clientes/back", method = RequestMethod.POST)
    public ResponseEntity<Cliente> backNuevoCliente(@RequestBody Cliente cliente) {

        HttpEntity<Cliente> request = new HttpEntity<>(cliente);
        ResponseEntity<Cliente> exchange = restTemplate.exchange("http://localhost:8082/clientes/back",
                HttpMethod.POST,
                request, Cliente.class);

       // restTemplate.exchange("http://localhost:8088/correo/nuevoCliente",
         //       HttpMethod.GET, request,
          //      Cliente.class);

        return new ResponseEntity<>(exchange.getBody(), exchange.getStatusCode());
    }


    @RequestMapping(value = "/api/clientes", method = RequestMethod.PUT, consumes = "application/json")
    public ResponseEntity<Cliente> actualizar(@RequestBody Cliente cliente) {

        HttpEntity<Cliente> request = new HttpEntity<>(cliente);
        ResponseEntity<Cliente> exchange = restTemplate.exchange("http://localhost:8082/clientes/",
                HttpMethod.PUT,
                request, Cliente.class);

        return new ResponseEntity<>(exchange.getStatusCode());
    }


    @RequestMapping(value = "/api/clientes", method = RequestMethod.DELETE)
    public ResponseEntity<Cliente> borrar(@RequestParam("id") Integer id) {

        HttpEntity<Integer> request = new HttpEntity<>(id);

        ResponseEntity<Integer> exchange = restTemplate.exchange("http://localhost:8082/clientes/",
                HttpMethod.DELETE,
                request, Integer.class);

        return new ResponseEntity<>(exchange.getStatusCode());
    }


    @RequestMapping(value = "/api/cliente/paginacion", method = RequestMethod.GET, params = { "limit", "offset" }, produces = { "application/json" })
    public ResponseEntity<Cliente> clientesPaginacion(@RequestParam("limit") int limit,
                                                      @RequestParam("offset") int offset)
    {
        String url = "http://localhost:8082/clientes/paginacion";
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url)
                .queryParam("limit", limit)
                .queryParam("offset", offset);

        ResponseEntity<Cliente> responseEntity = restTemplate.exchange(builder.toUriString(),
                HttpMethod.GET,
                null, new ParameterizedTypeReference<Cliente>() {});

        return new ResponseEntity<>(responseEntity.getBody(), responseEntity.getStatusCode());
    }


    @GetMapping("/api/clientes/{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable int id) {

        HttpEntity<Integer> request = new HttpEntity<>(id);
        ResponseEntity<Cliente> exchange = restTemplate.exchange("http://localhost:8082/clientes/" + id,
                HttpMethod.GET,
                request, Cliente.class);

        return new ResponseEntity<>(exchange.getBody(), exchange.getStatusCode());
    }


    @GetMapping("/api/clientes/cantidad")
    public ResponseEntity<Integer> contarClientes() {

        ResponseEntity<Integer> exchange = restTemplate.exchange("http://localhost:8082/clientes/cantidad",
                HttpMethod.GET,
                null, Integer.class);

        return new ResponseEntity<>(exchange.getBody(), exchange.getStatusCode());
    }

}