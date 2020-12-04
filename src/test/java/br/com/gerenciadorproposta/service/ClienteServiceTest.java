package br.com.gerenciadorproposta.service;

import br.com.gerenciadorproposta.repository.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {

    @InjectMocks
    private ClienteService clienteService;

    @Mock
    private ClienteRepository clienteRepository;

    @Test
    public void findAllDeveChamarRepository() {
        clienteService.findAll();
        verify(clienteRepository).findAll();
    }

    @Test
    public void deleteDeveChamarRepository() {
        clienteService.delete(1L);
        verify(clienteRepository).deleteById(1L);
    }

}
