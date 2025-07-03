package br.com.bazarbooks.components.perfilusuario.provided.interfaces;

import br.com.bazarbooks.components.perfilusuario.internal.model.Address;
import br.com.bazarbooks.components.perfilusuario.internal.model.User;
import java.util.List;
import java.util.Optional;

public interface UserProfileProvidedInterface {
    User createUserProfile(User user);

    Optional<User> findUserProfileById(int id);

    List<User> findAllUserProfiles();

    User updateUserProfile(int id, User updatedUser);

    boolean deleteUserProfile(int id);

    Address addUserAddress(int userId, Address address);

    Optional<Address> findUserAddressById(int addressId);

    List<Address> findUserAddressesByUserId(int userId);

    Address updateUserAddress(int addressId, Address updatedAddress);

    boolean deleteUserAddress(int addressId);

    String uploadProfilePicture(int userId, byte[] imageData, String fileName); // ImageUploader
    
    String getUserAddressCoordinates(int addressId); // GeolocationProvider
}