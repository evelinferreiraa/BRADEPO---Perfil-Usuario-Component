package br.com.bazarbooks.components.perfilusuario.provided;

import io.github.lifveras.bredeco_pic_abstract.provided.InterfacePort;
import io.github.lifveras.bredeco_pic_abstract.required.PortOutbox;

import br.com.bazarbooks.components.perfilusuario.internal.model.Address;
import br.com.bazarbooks.components.perfilusuario.internal.model.User;
import br.com.bazarbooks.components.perfilusuario.internal.service.UserService;
import br.com.bazarbooks.components.perfilusuario.required.UserProfileOutbox;
import br.com.bazarbooks.components.perfilusuario.required.interfaces.GeolocationRequiredInterface;
import br.com.bazarbooks.components.perfilusuario.required.interfaces.ImageUploaderRequiredInterface;
import br.com.bazarbooks.components.perfilusuario.provided.interfaces.UserProfileProvidedInterface;

import java.util.List;
import java.util.Optional;

public class UserProfileConcreteInterfacePort extends InterfacePort implements UserProfileProvidedInterface {

    private UserService userService;
    private UserProfileOutbox outbox;

    public UserProfileConcreteInterfacePort(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void initialize() {
        System.out.println("DEBUG: Port do Perfil do Usuário inicializado.");
    }

    public void setOutbox(PortOutbox outbox) {
        if (outbox instanceof UserProfileOutbox) {
            this.outbox = (UserProfileOutbox) outbox;
            this.id = "userProfilePort";
        } else {
            throw new IllegalArgumentException("Outbox fornecida não é do tipo UserProfileOutbox.");
        }
    }

    @Override
    public User createUserProfile(User user) {
        return userService.createUser(user);
    }

    @Override
    public Optional<User> findUserProfileById(int id) {
        return userService.findUserById(id);
    }

    @Override
    public List<User> findAllUserProfiles() {
        return userService.findAllUsers();
    }

    @Override
    public User updateUserProfile(int id, User updatedUser) {
        return userService.updateUser(id, updatedUser);
    }

    @Override
    public boolean deleteUserProfile(int id) {
        return userService.deleteUser(id);
    }

    @Override
    public Address addUserAddress(int userId, Address address) {
        return userService.addAddressToUser(userId, address);
    }

    @Override
    public Optional<Address> findUserAddressById(int addressId) {
        return userService.findAddressById(addressId);
    }

    @Override
    public List<Address> findUserAddressesByUserId(int userId) {
        return userService.findAddressesByUserId(userId);
    }

    @Override
    public Address updateUserAddress(int addressId, Address updatedAddress) {
        return userService.updateAddress(addressId, updatedAddress);
    }

    @Override
    public boolean deleteUserAddress(int addressId) {
        return userService.deleteAddress(addressId);
    }

    @Override
    public String uploadProfilePicture(int userId, byte[] imageData, String fileName) {
        if (this.outbox == null) {
            throw new IllegalStateException("Outbox não está conectada ou não é do tipo correto.");
        }
        ImageUploaderRequiredInterface uploader = (ImageUploaderRequiredInterface) this.outbox
                .getInterface("imageUploader");
        if (uploader == null) {
            throw new UnsupportedOperationException("Serviço de upload de imagem não disponível.");
        }
        return uploader.uploadProfileImage(imageData, fileName);
    }

    @Override
    public String getUserAddressCoordinates(int addressId) {
        if (this.outbox == null) {
            throw new IllegalStateException("Outbox não está conectada ou não é do tipo correto.");
        }
        Optional<Address> addressOpt = userService.findAddressById(addressId);
        if (addressOpt.isEmpty()) {
            return null;
        }
        Address address = addressOpt.get();

        GeolocationRequiredInterface geoProvider = (GeolocationRequiredInterface) this.outbox
                .getInterface("geolocationProvider");
        if (geoProvider == null) {
            throw new UnsupportedOperationException("Serviço de geolocalização não disponível.");
        }
        return geoProvider.getCoordinates(address);
    }
}