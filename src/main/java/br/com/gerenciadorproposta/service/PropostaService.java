package br.com.gerenciadorproposta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gerenciadorproposta.exception.BusinessException;
import br.com.gerenciadorproposta.exception.EntityNotFoundException;
import br.com.gerenciadorproposta.model.Proposta;
import br.com.gerenciadorproposta.service.ClienteService;
import br.com.gerenciadorproposta.repository.PropostaRepository;

@Service
public class PropostaService implements CrudService<Proposta, Long> {
    @Autowired
    private PropostaRepository propostaRepository;

    @Autowired
    private ClienteService clienteService;

    public List<Proposta> findByCliente(Long idCliente) {
        return propostaRepository.findByClienteId(idCliente);
    }

    @Override
    public List<Proposta> findAll() {
        return propostaRepository.findAll();
    }

    @Override
    public Proposta findOne(Long id) throws EntityNotFoundException, BusinessException {
        return propostaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Propostas não encontrada."));
    }

    @Override
    public void delete(Long id) throws EntityNotFoundException, BusinessException {
        propostaRepository.deleteById(id);
    }

    @Override
    public Proposta update(Long id, Proposta proposta) throws EntityNotFoundException, BusinessException {
        try {
            clienteService.findOne(proposta.getCliente().getId());
        } catch (EntityNotFoundException e) {
            throw new BusinessException(e.getMessage());
        }
        findOne(id);
        return propostaRepository.save(proposta);
    }

    @Override
    public Proposta save(Proposta proposta) throws EntityNotFoundException, BusinessException {
        try {
            clienteService.findOne(proposta.getCliente().getId());
        } catch (EntityNotFoundException e) {
            throw new BusinessException(e.getMessage());
        }
        return propostaRepository.save(proposta);
    }

}