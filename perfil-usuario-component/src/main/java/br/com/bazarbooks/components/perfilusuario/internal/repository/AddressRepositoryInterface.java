package br.com.bazarbooks.components.perfilusuario.internal.repository;

import br.com.bazarbooks.components.perfilusuario.internal.model.Address;
import java.util.List;
import java.util.Optional;

public interface AddressRepositoryInterface {
    Address save(Address address);

    Optional<Address> findById(int id);

    List<Address> findByUserId(int userId);

    List<Address> findAll();

    void deleteById(int id);

    boolean existsById(int id);

    void deleteAllByUserId(int userId);
}