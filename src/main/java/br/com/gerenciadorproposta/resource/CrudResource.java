package br.com.gerenciadorproposta.resource;

import java.util.List;

public interface CrudResource<T> {

    List<?> findAll();

    T findOne(Long id);

    T save(T t);

    T update(Long id, T t);

    void delete(Long id);
    
}