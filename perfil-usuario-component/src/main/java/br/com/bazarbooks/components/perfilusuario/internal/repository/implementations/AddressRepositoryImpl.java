package br.com.bazarbooks.components.perfilusuario.internal.repository.implementations;

import br.com.bazarbooks.components.perfilusuario.internal.model.Address;
import br.com.bazarbooks.components.perfilusuario.internal.repository.AddressRepositoryInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class AddressRepositoryImpl implements AddressRepositoryInterface {
    private static final Map<Integer, Address> addresses = new HashMap<>();
    private static final AtomicInteger nextId = new AtomicInteger(1);

    public static void clear() {
        addresses.clear();
        nextId.set(1);
    }

    @Override
    public Address save(Address address) {
        if (address.getId() == 0) {
            address.setId(nextId.getAndIncrement());
        }
        addresses.put(address.getId(), address);
        return address;
    }

    @Override
    public Optional<Address> findById(int id) {
        return Optional.ofNullable(addresses.get(id));
    }

    @Override
    public List<Address> findByUserId(int userId) {
        return addresses.values().stream()
                .filter(address -> address.getUserId() == userId)
                .collect(Collectors.toList());
    }

    @Override
    public List<Address> findAll() {
        return new ArrayList<>(addresses.values());
    }

    @Override
    public void deleteById(int id) {
        addresses.remove(id);
    }

    @Override
    public boolean existsById(int id) {
        return addresses.containsKey(id);
    }

    @Override
    public void deleteAllByUserId(int userId) {
        List<Address> toRemove = addresses.values().stream()
                .filter(address -> address.getUserId() == userId)
                .collect(Collectors.toList());
        toRemove.forEach(address -> addresses.remove(address.getId()));
    }
}