package br.com.gerenciadorproposta.service;

import br.com.gerenciadorproposta.exception.EntityNotFoundException;
import br.com.gerenciadorproposta.repository.PropostaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
    public void listarTodosDeveChamarRepository() {
        propostaService.findAll();
        verify(propostaRepository).findAll();
    }

    @Test
    public void buscarUmDeveLancarExcecaoCorretaAoNaoEncontrarProposta() {
        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            when(propostaRepository.getOne(1L))
                    .thenThrow(EntityNotFoundException.class);
            propostaService.findOne(1L);
        });
    }
}
