package br.com.gerenciadorproposta.service;

import br.com.gerenciadorproposta.exception.EntityNotFoundException;
import br.com.gerenciadorproposta.model.Cliente;
import br.com.gerenciadorproposta.repository.ClienteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {

    @InjectMocks
    private ClienteService clienteService;

    @Mock
    private ClienteRepository clienteRepository;

    @Test
    public void findOneDeveBuscarCliente() {
        when(clienteRepository.findById(1L))
                .thenReturn(Optional.of(new Cliente()));
        clienteService.findOne(1L);
        verify(clienteRepository).findById(1L);
    }

    @Test
    public void findOneDeveLancarExcecaoAoNaoEncontrarRegistro() {
        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            clienteService.findOne(1L);
        });
    }

    @Test
    public void deleteDeveApagarCliente() {
        clienteService.delete(1L);
        verify(clienteRepository).deleteById(1L);
    }

    @Test
    public void saveDeveSalvarCliente() {
        Cliente cliente = new Cliente();
        clienteService.save(cliente);
        verify(clienteRepository).save(cliente);
    }

    @Test
    public void updateDeveLancarExcecaoAoNaoEncontrarRegistro() {
        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            clienteService.update(1L, new Cliente());
        });
    }

    @Test
    public void updateDeveAtualizarCliente() {
        when(clienteRepository.findById(1L))
                .thenReturn(Optional.of(new Cliente()));
        clienteService.update(1L, new Cliente());
    }

    @Test
    public void findAllDeveListarClientes() {
        clienteService.findAll();
        verify(clienteRepository).findAll();
    }

}
