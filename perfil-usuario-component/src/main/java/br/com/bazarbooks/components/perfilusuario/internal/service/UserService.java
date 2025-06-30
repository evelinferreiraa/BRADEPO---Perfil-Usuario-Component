package br.com.bazarbooks.components.perfilusuario.internal.service;

import br.com.bazarbooks.components.perfilusuario.internal.model.Address;
import br.com.bazarbooks.components.perfilusuario.internal.model.User;
import br.com.bazarbooks.components.perfilusuario.internal.repository.AddressRepositoryInterface;
import br.com.bazarbooks.components.perfilusuario.internal.repository.UserRepositoryInterface;

import java.util.List;
import java.util.Optional;

public class UserService {
    private final UserRepositoryInterface userRepository;
    private final AddressRepositoryInterface addressRepository;

    public UserService(UserRepositoryInterface userRepository, AddressRepositoryInterface addressRepository) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
    }

    public User createUser(User user) {
        if (user.getName() == null || user.getName().isEmpty() || user.getEmail() == null
                || user.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Nome e email do usuário são obrigatórios.");
        }
        return userRepository.save(user);
    }

    public Optional<User> findUserById(int id) {
        return userRepository.findById(id);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(int id, User updatedUser) {
        Optional<User> existingUserOpt = userRepository.findById(id);
        if (existingUserOpt.isEmpty()) {
            return null;
        }
        User existingUser = existingUserOpt.get();
        existingUser.setName(updatedUser.getName());
        existingUser.setEmail(updatedUser.getEmail());
        return userRepository.save(existingUser);
    }

    public boolean deleteUser(int id) {
        if (!userRepository.existsById(id)) {
            return false;
        }
        userRepository.deleteById(id);
        addressRepository.deleteAllByUserId(id);
        return true;
    }

    public Address addAddressToUser(int userId, Address address) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            return null;
        }
        address.setUserId(userId);
        return addressRepository.save(address);
    }

    public Optional<Address> findAddressById(int addressId) {
        return addressRepository.findById(addressId);
    }

    public List<Address> findAddressesByUserId(int userId) {
        return addressRepository.findByUserId(userId);
    }

    public Address updateAddress(int addressId, Address updatedAddress) {
        Optional<Address> existingAddressOpt = addressRepository.findById(addressId);
        if (existingAddressOpt.isEmpty()) {
            return null;
        }
        Address existingAddress = existingAddressOpt.get();

        if (updatedAddress.getUserId() != existingAddress.getUserId()) {
            throw new IllegalArgumentException("Não é permitido alterar o usuário associado a um endereço existente.");
        }

        existingAddress.setStreet(updatedAddress.getStreet());
        existingAddress.setNumber(updatedAddress.getNumber());
        existingAddress.setComplement(updatedAddress.getComplement());
        existingAddress.setDistrict(updatedAddress.getDistrict());
        existingAddress.setCity(updatedAddress.getCity());
        existingAddress.setState(updatedAddress.getState());
        existingAddress.setPostalCode(updatedAddress.getPostalCode());
        existingAddress.setLatitude(updatedAddress.getLatitude());
        existingAddress.setLongitude(updatedAddress.getLongitude());

        return addressRepository.save(existingAddress);
    }

    public boolean deleteAddress(int addressId) {
        if (!addressRepository.existsById(addressId)) {
            return false;
        }
        addressRepository.deleteById(addressId);
        return true;
    }
}