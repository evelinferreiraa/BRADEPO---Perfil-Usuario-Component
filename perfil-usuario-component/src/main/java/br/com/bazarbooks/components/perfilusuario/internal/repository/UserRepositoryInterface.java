package br.com.bazarbooks.components.perfilusuario.internal.repository;

import br.com.bazarbooks.components.perfilusuario.internal.model.User;
import java.util.List;
import java.util.Optional;

public interface UserRepositoryInterface {
    User save(User user);

    Optional<User> findById(int id);

    List<User> findAll();

    void deleteById(int id);

    boolean existsById(int id);
}