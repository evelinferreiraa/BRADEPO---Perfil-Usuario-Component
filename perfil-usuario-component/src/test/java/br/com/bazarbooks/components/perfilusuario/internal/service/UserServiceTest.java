package br.com.bazarbooks.components.perfilusuario.internal.service;

import br.com.bazarbooks.components.perfilusuario.internal.model.User;
import br.com.bazarbooks.components.perfilusuario.internal.model.Address;
import br.com.bazarbooks.components.perfilusuario.internal.repository.implementations.UserRepositoryImpl;
import br.com.bazarbooks.components.perfilusuario.internal.repository.implementations.AddressRepositoryImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

public class UserServiceTest {

    private UserService userService;

    @BeforeEach
    void setUp() {
        UserRepositoryImpl.clear();
        AddressRepositoryImpl.clear();

        userService = new UserService(new UserRepositoryImpl(), new AddressRepositoryImpl());
    }

    @Test
    void testCreateUserSuccessfully() {
        User newUser = new User("Alice Silva", "alice@example.com");
        User createdUser = userService.createUser(newUser);

        assertNotNull(createdUser, "O usuário criado não deve ser null.");
        assertNotEquals(0, createdUser.getId(), "O ID do usuário deve ser gerado e não 0.");
        assertEquals("Alice Silva", createdUser.getName(), "O nome do usuário deve corresponder.");
        assertEquals("alice@example.com", createdUser.getEmail(), "O email do usuário deve corresponder.");

        assertTrue(userService.findUserById(createdUser.getId()).isPresent(),
                "O usuário deve ser encontrado no repositório.");
    }

    @Test
    void testCreateUserWithMissingName() {
        User newUser = new User(null, "test@example.com");

        assertThrows(IllegalArgumentException.class, () -> {
            userService.createUser(newUser);
        }, "Deve lançar IllegalArgumentException para nome ausente.");
    }

    @Test
    void testFindUserByIdNotFound() {
        Optional<User> foundUser = userService.findUserById(999); // ID que certamente não existe
        assertFalse(foundUser.isPresent(), "Não deve encontrar usuário com ID inexistente.");
    }

    @Test
    void testUpdateExistingUser() {
        User userToUpdate = userService.createUser(new User("Original Name", "original@email.com"));
        assertNotNull(userToUpdate);

        User updatedData = new User("Updated Name", "updated@email.com");
        User result = userService.updateUser(userToUpdate.getId(), updatedData);

        assertNotNull(result, "O usuário atualizado não deve ser null.");
        assertEquals("Updated Name", result.getName());
        assertEquals("updated@email.com", result.getEmail());

        Optional<User> found = userService.findUserById(userToUpdate.getId());
        assertTrue(found.isPresent());
        assertEquals("Updated Name", found.get().getName());
        assertEquals("updated@email.com", found.get().getEmail());
    }

    @Test
    void testUpdateNonExistingUser() {
        User nonExistingUser = new User("Non Existing", "non@existing.com");
        User result = userService.updateUser(999, nonExistingUser);
        assertNull(result, "Não deve atualizar um usuário inexistente, deve retornar null.");
    }

    @Test
    void testDeleteUserSuccessfully() {
        User userToDelete = userService.createUser(new User("Delete Me", "delete@me.com"));
        assertNotNull(userToDelete);

        boolean deleted = userService.deleteUser(userToDelete.getId());

        assertTrue(deleted, "Usuário deve ser deletado com sucesso.");
        assertFalse(userService.findUserById(userToDelete.getId()).isPresent(),
                "Usuário não deve mais existir no repositório.");
    }

    @Test
    void testDeleteNonExistingUser() {
        boolean deleted = userService.deleteUser(999);
        assertFalse(deleted, "Não deve tentar deletar um usuário inexistente, deve retornar false.");
    }

    @Test
    void testAddAddressToUserSuccessfully() {
        User user = userService.createUser(new User("Address User", "address@user.com"));
        assertNotNull(user);

        Address newAddress = new Address("Street A", 123, "Apt 1", "Dist A", "City A", "State A", "12345-000", 0.0, 0.0,
                user.getId());
        Address createdAddress = userService.addAddressToUser(user.getId(), newAddress);

        assertNotNull(createdAddress, "O endereço criado não deve ser null.");
        assertNotEquals(0, createdAddress.getId(), "O ID do endereço deve ser gerado.");
        assertEquals(user.getId(), createdAddress.getUserId(), "O ID do usuário no endereço deve corresponder.");
        assertEquals("Street A", createdAddress.getStreet());

        assertTrue(userService.findAddressById(createdAddress.getId()).isPresent(),
                "O endereço deve ser encontrado no repositório.");
    }

    @Test
    void testAddAddressToNonExistingUser() {
        Address newAddress = new Address("Street B", 456, "", "Dist B", "City B", "State B", "54321-000", 0.0, 0.0,
                999);
        Address createdAddress = userService.addAddressToUser(999, newAddress); // Usuário inexistente

        assertNull(createdAddress, "Não deve adicionar endereço a usuário inexistente, deve retornar null.");
    }

    @Test
    void testFindAddressesByUserId() {
        User user1 = userService.createUser(new User("User 1", "u1@email.com"));
        User user2 = userService.createUser(new User("User 2", "u2@email.com"));

        userService.addAddressToUser(user1.getId(),
                new Address("Rua A", 1, "", "", "", "", "", 0.0, 0.0, user1.getId()));
        userService.addAddressToUser(user1.getId(),
                new Address("Rua B", 2, "", "", "", "", "", 0.0, 0.0, user1.getId()));
        userService.addAddressToUser(user2.getId(),
                new Address("Rua C", 3, "", "", "", "", "", 0.0, 0.0, user2.getId()));

        List<Address> user1Addresses = userService.findAddressesByUserId(user1.getId());
        assertEquals(2, user1Addresses.size());
        assertTrue(user1Addresses.stream().allMatch(a -> a.getUserId() == user1.getId()));

        List<Address> user2Addresses = userService.findAddressesByUserId(user2.getId());
        assertEquals(1, user2Addresses.size());
        assertTrue(user2Addresses.stream().allMatch(a -> a.getUserId() == user2.getId()));

        List<Address> noAddresses = userService.findAddressesByUserId(999);
        assertTrue(noAddresses.isEmpty());
    }

    @Test
    void testUpdateAddressSuccessfully() {
        User user = userService.createUser(new User("User", "user@email.com"));
        Address originalAddress = userService.addAddressToUser(user.getId(),
                new Address("Old Street", 10, "", "", "", "", "", 0.0, 0.0, user.getId()));
        assertNotNull(originalAddress);

        Address updatedData = new Address("New Street", 20, "Suite", "Central", "City", "State", "00000-000", 1.0, 1.0,
                user.getId());
        Address result = userService.updateAddress(originalAddress.getId(), updatedData);

        assertNotNull(result);
        assertEquals("New Street", result.getStreet());
        assertEquals(20, result.getNumber());
        assertEquals("Suite", result.getComplement());

        Optional<Address> found = userService.findAddressById(originalAddress.getId());
        assertTrue(found.isPresent());
        assertEquals("New Street", found.get().getStreet());
    }

    @Test
    void testUpdateAddressNotFound() {
        User user = userService.createUser(new User("User", "user@email.com"));
        Address updatedData = new Address("Street", 10, "", "", "", "", "", 0.0, 0.0, user.getId());
        Address result = userService.updateAddress(999, updatedData);
        assertNull(result, "Não deve atualizar um endereço inexistente, deve retornar null.");
    }

    @Test
    void testUpdateAddressUserIdMismatch() {
        User user1 = userService.createUser(new User("User1", "u1@email.com"));
        User user2 = userService.createUser(new User("User2", "u2@email.com"));
        Address addr1 = userService.addAddressToUser(user1.getId(),
                new Address("Rua X", 1, "", "", "", "", "", 0.0, 0.0, user1.getId()));

        Address updatedData = new Address("Rua Y", 2, "", "", "", "", "", 0.0, 0.0, user2.getId()); 

        assertThrows(IllegalArgumentException.class, () -> {
            userService.updateAddress(addr1.getId(), updatedData);
        }, "Deve lançar IllegalArgumentException se o userId no endereço atualizado for diferente do original.");
    }

    @Test
    void testDeleteAddressSuccessfully() {
        User user = userService.createUser(new User("User", "user@email.com"));
        Address addressToDelete = userService.addAddressToUser(user.getId(),
                new Address("Delete Street", 1, "", "", "", "", "", 0.0, 0.0, user.getId()));
        assertNotNull(addressToDelete);

        boolean deleted = userService.deleteAddress(addressToDelete.getId());
        assertTrue(deleted, "Endereço deve ser deletado com sucesso.");
        assertFalse(userService.findAddressById(addressToDelete.getId()).isPresent(),
                "Endereço não deve mais existir.");
    }
}