package br.com.bazarbooks.components.perfilusuario.provided;

import io.github.lifveras.bredeco_pic_abstract.provided.ComponentInterface;
import io.github.lifveras.bredeco_pic_abstract.provided.InterfacePort;
import io.github.lifveras.bredeco_pic_abstract.required.PortOutbox;

import br.com.bazarbooks.components.perfilusuario.internal.repository.AddressRepositoryInterface;
import br.com.bazarbooks.components.perfilusuario.internal.repository.UserRepositoryInterface;
import br.com.bazarbooks.components.perfilusuario.internal.repository.implementations.AddressRepositoryImpl;
import br.com.bazarbooks.components.perfilusuario.internal.repository.implementations.UserRepositoryImpl;
import br.com.bazarbooks.components.perfilusuario.internal.service.UserService;
import br.com.bazarbooks.components.perfilusuario.required.UserProfileOutbox;
// import br.com.bazarbooks.components.perfilusuario.provided.UserProfileConcreteInterfacePort;

public class UserProfileConcreteInterface extends ComponentInterface {

    private UserService userService;
    private UserProfileConcreteInterfacePort userProfilePort;
    private UserProfileOutbox userProfileOutbox;

    public UserProfileConcreteInterface() {
    }

    @Override
    public void initialize() {
        UserRepositoryInterface userRepository = new UserRepositoryImpl();
        AddressRepositoryInterface addressRepository = new AddressRepositoryImpl();

        this.userService = new UserService(userRepository, addressRepository);

        this.userProfilePort = new UserProfileConcreteInterfacePort(userService);

        this.userProfileOutbox = new UserProfileOutbox();

        this.userProfilePort.setOutbox(this.userProfileOutbox);

        System.out.println("DEBUG: Componente Perfil do Usuário inicializado.");
    }

    @Override
    public InterfacePort getPort(String id) {
        if ("UserProfileServicePort".equals(id) || "default".equals(id)) {
            return this.userProfilePort;
        }
        throw new IllegalArgumentException("Port '" + id + "' não provida por este componente.");
    }

    public void setOutbox(PortOutbox outbox) {
        System.out.println("DEBUG: setOutbox chamado no UserProfileConcreteInterface. Recebido tipo: "
                + outbox.getClass().getName());
    }
}