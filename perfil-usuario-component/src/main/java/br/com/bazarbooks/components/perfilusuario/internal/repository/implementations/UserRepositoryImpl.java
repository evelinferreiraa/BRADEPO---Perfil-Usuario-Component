package br.com.bazarbooks.components.perfilusuario.internal.repository.implementations;

import br.com.bazarbooks.components.perfilusuario.internal.model.User;
import br.com.bazarbooks.components.perfilusuario.internal.repository.UserRepositoryInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class UserRepositoryImpl implements UserRepositoryInterface {
    private static final Map<Integer, User> users = new HashMap<>();
    private static final AtomicInteger nextId = new AtomicInteger(1);

    public static void clear() {
        users.clear();
        nextId.set(1);
    }

    @Override
    public User save(User user) {
        if (user.getId() == 0) {
            user.setId(nextId.getAndIncrement());
        }
        users.put(user.getId(), user);
        return user;
    }

    @Override
    public Optional<User> findById(int id) {
        return Optional.ofNullable(users.get(id));
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }

    @Override
    public void deleteById(int id) {
        users.remove(id);
    }

    @Override
    public boolean existsById(int id) {
        return users.containsKey(id);
    }
}