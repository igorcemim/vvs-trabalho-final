package br.com.gerenciadorproposta.service;

import br.com.gerenciadorproposta.exception.EntityNotFoundException;
import br.com.gerenciadorproposta.model.Cliente;
import br.com.gerenciadorproposta.model.Proposta;
import br.com.gerenciadorproposta.repository.PropostaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PropostaServiceTest {

    @InjectMocks
    private PropostaService propostaService;
    @Mock
    private PropostaRepository propostaRepository;
    @Mock
    private ClienteService clienteService;

    @Test
    public void findOneDeveBuscarProposta() {
        Proposta proposta = stubProposta();
        when(propostaRepository.findById(1L))
                .thenReturn(Optional.of(proposta));
        propostaService.findOne(1L);
        verify(propostaRepository).findById(1L);
    }

    @Test
    public void findOneDeveLancarExcecaoAoNaoEncontrarRegistro() {
        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            propostaService.findOne(1L);
        });
    }

    @Test
    public void deleteDeveApagarProposta() {
        propostaService.delete(1L);
        verify(propostaRepository).deleteById(1L);
    }

    @Test
    public void saveDeveSalvarProposta() {
        Proposta proposta = stubProposta();
        propostaService.save(proposta);
        verify(propostaRepository).save(proposta);
    }

    @Test
    public void updateDeveLancarExcecaoAoNaoEncontrarRegistro() {
        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            propostaService.update(1L, stubProposta());
        });
    }

    @Test
    public void updateDeveAtualizarProposta() {
        Proposta proposta = stubProposta();
        when(propostaRepository.findById(1L))
                .thenReturn(Optional.of(proposta));
        propostaService.update(1L, proposta);
    }

    private Proposta stubProposta() {
        Proposta proposta = new Proposta();
        proposta.setCliente(new Cliente());
        return proposta;
    }

    @Test
    public void findAllDeveListarProposta() {
        propostaService.findAll();
        verify(propostaRepository).findAll();
    }

    @Test
    public void saveDeveRetornarOMesmoObjetoNaoModificado() {
        Proposta proposta = new Proposta();
        proposta.setCliente(new Cliente());

        when(propostaRepository.save(any(Proposta.class)))
                .thenAnswer(c -> c.getArgument(0));

        Proposta retorno = propostaService.save(proposta);
        Assertions.assertEquals(proposta, retorno);
    }

}
