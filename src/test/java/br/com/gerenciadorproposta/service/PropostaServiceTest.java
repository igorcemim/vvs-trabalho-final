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
    public void findAllDeveChamarRepository() {
        propostaService.findAll();
        verify(propostaRepository).findAll();
    }

    @Test
    public void findOneDeveLancarExcecaoCorretaAoNaoEncontrarProposta() {
        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            when(propostaRepository.findById(1L))
                    .thenThrow(EntityNotFoundException.class);
            propostaService.findOne(1L);
        });
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
