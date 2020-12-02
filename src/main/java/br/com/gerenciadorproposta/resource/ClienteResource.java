package br.com.gerenciadorproposta.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.gerenciadorproposta.model.Cliente;
import br.com.gerenciadorproposta.resource.ApiControllerAdvice.ApiError;
import br.com.gerenciadorproposta.service.ClienteService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/clientes")
public class ClienteResource implements CrudResource<Cliente> {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Cliente> findAll() {
        return clienteService.findAll();
    }

    @GetMapping("/{id}")
    public Cliente findOne(@PathVariable("id") Long id) {
        return clienteService.findOne(id);
    }

    @PostMapping
    @ApiResponses(
        @ApiResponse(code = 400, message = "Bad request", response = ApiError.class)
    )
    @ResponseStatus(code = HttpStatus.CREATED)
    public Cliente save(@Valid @RequestBody Cliente cliente) {
        return clienteService.save(cliente);
    }

    @PutMapping("/{id}")
    @ApiResponses(
        @ApiResponse(code = 400, message = "Bad request", response = ApiError.class)
    )
    public Cliente update(@PathVariable("id") Long id, @Valid @RequestBody Cliente cliente) {
        return clienteService.update(id, cliente);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        clienteService.delete(id);
    }
}