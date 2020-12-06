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

import br.com.gerenciadorproposta.model.Proposta;
import br.com.gerenciadorproposta.resource.ApiControllerAdvice.ApiError;
import br.com.gerenciadorproposta.service.PropostaService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/propostas")
public class PropostaResource implements CrudResource<Proposta> {

    @Autowired
    private PropostaService propostaService;

    @GetMapping
    public List<Proposta> findAll() {
        return propostaService.findAll();
    }

    @GetMapping("/cliente/{id}")
    public List<Proposta> findByid(@PathVariable("id") Long id) {
        return propostaService.findByCliente(id);
    }

    @GetMapping("/{id}")
    public Proposta findOne(@PathVariable("id") Long id) {
        return propostaService.findOne(id);
    }

    @PostMapping
    @ApiResponses(@ApiResponse(code = 400, message = "Bad request", response = ApiError.class))
    @ResponseStatus(code = HttpStatus.CREATED)
    public Proposta save(@Valid @RequestBody Proposta proposta) {
        return propostaService.save(proposta);
    }

    @PutMapping("/{id}")
    @ApiResponses(@ApiResponse(code = 400, message = "Bad request", response = ApiError.class))
    public Proposta update(@PathVariable("id") Long id, @Valid @RequestBody Proposta proposta) {
        return propostaService.update(id, proposta);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        propostaService.delete(id);
    }
}
