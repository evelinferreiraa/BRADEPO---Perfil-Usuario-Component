# Bazar Books - Componente Perfil do Usuário

Este repositório contém o **componente de software para gerenciamento de perfis de usuário do Bazar Books**, implementando o **Padrão de Interface de Componente (PIC)**. Ele utiliza uma **abordagem orientada à interface**, com suporte a **injeção de dependências** e separação clara entre **interfaces providas** e **requeridas**.

O componente foi desenvolvido seguindo os princípios da **Programação Orientada a Objetos (POO)** e **SOLID**.

## Como começar

Para começar a trabalhar com este componente:

1. Faça um **fork** deste repositório clicando no botão `Fork` no canto superior direito.
2. Clone o repositório forkado em sua máquina:

```bash
git clone [https://github.com/evelinferreiraa/BRADEPO-perfil-usuario-component](https://github.com/evelinferreiraa/BRADEPO-perfil-usuario-component)
````

## Estrutura do Projeto

A estrutura foi desenhada para promover **modularidade, acoplamento fraco** e **alta coesão**, com as seguintes divisões:

```
src/main/java/br/com/bazarbooks/components/perfilusuario/
├── internal/
│   └── [Classes internas de implementação do componente, como UserService, UserRepositoryImpl, AddressRepositoryImpl, User e Address]
│
├── provided/
│   └── interfaces/
│       └── UserProfileProvidedInterface.java  <-- Interface provida pelo componente de Perfil de Usuário
│
├── required/
│   └── interfaces/
│       └── ImageUploaderRequiredInterface.java  <-- Interface requerida para upload de imagens
│       └── GeolocationRequiredInterface.java   <-- Interface requerida para geolocalização
│
├── UserProfileConcreteInterface.java          <-- Implementação concreta da interface do componente
└── UserProfileConcreteInterfacePort.java      <-- Porto concreto do componente de Perfil de Usuário

```

### 📦 `internal/`

Implemente aqui as **classes internas do seu componente**, utilizando práticas de POO e os princípios do SOLID. Neste projeto, você encontrará classes como `UserService`, `UserRepositoryImpl`, `AddressRepositoryImpl`, `User` e `Address`.

### 📦 `provided/interfaces/`

Declare aqui as **interfaces providas pelo seu componente**, ou seja, os serviços que ele disponibiliza para outros componentes ou sistemas. A principal interface provida é `UserProfileProvidedInterface`.

### 📦 `required/interfaces/`

Declare aqui as **interfaces requeridas**, ou seja, os serviços externos dos quais seu componente depende. Neste componente, temos as interfaces `ImageUploaderRequiredInterface` e `GeolocationRequiredInterface`.

-----

## Dependência

Este componente depende do pacote de **abstrações do PIC** disponível em:

📦 Repositório: [github.com/lifveras/bradeco\_pic\_abstract](https://github.com/lifveras/bradeco_pic_abstract)

A seguinte dependência já está no `pom.xml`:

```xml
<dependency>
    <groupId>io.github.lifveras</groupId>
    <artifactId>bradeco_pic_abstract</artifactId>
    <version>1.0.0</version>
</dependency>
```

-----

## Comece a desenvolver

1.  O componente `perfil-usuario` já implementa a interface provida `UserProfileProvidedInterface`.
2.  As interfaces requeridas `ImageUploaderRequiredInterface` e `GeolocationRequiredInterface` já estão definidas.
3.  A interface concreta do seu componente é `UserProfileConcreteInterface`.
4.  O porto concreto do seu componente é `UserProfileConcreteInterfacePort`.
5.  As classes internas do componente, localizadas em `internal/`, já foram implementadas aplicando boas práticas de design de código (SOLID).

-----

## Sobre o autor do padrão

O padrão utilizado neste projeto é uma abstração inspirada no modelo proposto pelo Prof. Ricardo Silva.

📘 Livro: [Desenvolvimento Orientado a Componentes com UML](https://www.amazon.com.br/Desenvolvimento-orientado-componentes-com-UML-ebook/dp/B07DYDSMCZ/ref=sr_1_1)

📺 Canal do YouTube - Parte Teórica: [Parte Teórica](https://www.youtube.com/watch?v=0BmWe7d17NU&list=PLQb3t1uw-rpFIPbyWZCfOc9CTN5chPa0d)

📺 Canal do YouTube - Parte Prática: [Parte Prática](https://www.youtube.com/watch?v=4mnZnNAYHKc&list=PLQb3t1uw-rpHXs0N674qsdYB_Dlgoriy3)

-----

## Autor do Componente

  * Évelin Ferreira da Silva
  * [evelin.f@aluno.ifsp.edu.br](mailto:evelin.f@aluno.ifsp.edu.br)
  * [@evelinferreiraa no GitHub](https://www.google.com/search?q=https://github.com/evelinferreiraa)

## Autor do Template (Original)

  * Luiz Gustavo Véras
  * [gustavo\_veras@ifsp.edu.br](mailto:gustavo_veras@ifsp.edu.br)
  * [@lifveras no GitHub](https://github.com/lifveras)
