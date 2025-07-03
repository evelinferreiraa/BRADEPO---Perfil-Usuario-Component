package br.com.bazarbooks.components.perfilusuario;

import br.com.bazarbooks.components.perfilusuario.provided.UserProfileConcreteInterface;
import br.com.bazarbooks.components.perfilusuario.provided.interfaces.UserProfileProvidedInterface;
import br.com.bazarbooks.components.perfilusuario.internal.model.User; 
import br.com.bazarbooks.components.perfilusuario.internal.model.Address; 

import java.util.Optional;
import java.util.List;

public class App {
    public static void main(String[] args) {
        System.out.println("--- Testando Componente 'Perfil do Usuário' como Dependência ---");

        // 1. Instanciar o Componente 
        UserProfileConcreteInterface perfilComponent = new UserProfileConcreteInterface();

        // 2. Inicializar o Componente
        perfilComponent.initialize();

        // 3. Obter a Interface Provida do Componente
        UserProfileProvidedInterface perfilService = 
            (UserProfileProvidedInterface) perfilComponent.getPort("UserProfileServicePort"); 

        if (perfilService == null) {
            System.out.println("ERRO: Serviço de Perfil do Usuário não obtido do componente.");
            return;
        }

        // --- Métodos do Componente ---

        // Criar um usuário
        User novoUser = new User("Luis Teste", "luis@teste.com");
        User userCriado = perfilService.createUserProfile(novoUser);
        System.out.println("Usuário criado: " + userCriado);

        // Buscar o usuário
        Optional<User> userEncontrado = perfilService.findUserProfileById(userCriado.getId());
        userEncontrado.ifPresent(u -> System.out.println("Usuário encontrado: " + u));

        // Adicionar um endereço
        Address novoEndereco = new Address("Av. Teste", 123, "Sala 1", "Bairro Teste", "Cidade Teste", "TS", "12345-678", 0.0, 0.0, userCriado.getId());
        Address enderecoCriado = perfilService.addUserAddress(userCriado.getId(), novoEndereco);
        System.out.println("Endereço adicionado: " + enderecoCriado);

        // Obter coordenadas (usa serviço requerido simulado)
        String coordenadas = perfilService.getUserAddressCoordinates(enderecoCriado.getId());
        System.out.println("Coordenadas do endereço: " + coordenadas);

        // Listar todos os usuários
        List<User> todosUsuarios = perfilService.findAllUserProfiles();
        System.out.println("Todos os usuários no componente:");
        todosUsuarios.forEach(u -> System.out.println(" - " + u));

        // Teste de Upload de Imagem de Perfil 
        System.out.println("\n--- Testando Upload de Imagem de Perfil ---");
        byte[] imageData = "simulated image data".getBytes(); // Dados simulados da imagem
        String fileName = "profile_pic.png";
        String imageUrl = perfilService.uploadProfilePicture(userCriado.getId(), imageData, fileName);
        System.out.println("URL da imagem de perfil uploaded: " + imageUrl);
       
        System.out.println("--- Teste de Dependência Concluído ---");
    }
}