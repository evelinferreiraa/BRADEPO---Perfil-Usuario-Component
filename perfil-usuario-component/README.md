# Template de Componente PIC

Este repositório contém um **template para desenvolvimento de componentes Java** baseados no padrão de projeto **PIC (Processo de Integração de Componentes)**. Ele utiliza uma **abordagem orientada à interface**, com suporte a **injeção de dependências** e separação clara entre **interfaces providas** e **requeridas**.

O template foi pensado para ser usado como base em projetos que seguem os princípios da **Programação Orientada a Objetos (POO)** e **SOLID**.

---

## Como começar

Para criar seu próprio componente a partir deste template:

1. Faça um **fork** deste repositório clicando no botão `Fork` no canto superior direito.
2. Clone o repositório forkado em sua máquina:

```bash
git clone https://github.com/seu-usuario/seu-novo-componente.git
```

## Estrutura do Projeto

A estrutura foi desenhada para promover **modularidade, acoplamento fraco** e **alta coesão**, com as seguintes divisões:

```

src/main/java/seu/pacote/
├── internal/
│   └── \[Classes internas de implementação do componente]
│
├── provided/
│   └── interfaces/
│       └── MyComponentProvidedInterface.java  <-- substitua por sua interface provida
│
├── required/
│   └── interfaces/
│       └── MyComponentRequiredInterface.java  <-- substitua por sua interface requerida
│
├── MyComponentConcreteInterface.java          <-- substitua pela interface concreta do componente
└── MyComponentConcreteInterfacePort.java      <-- substitua pelo porto concreto do componente

````

### 📦 `internal/`
Implemente aqui as **classes internas do seu componente**, utilizando práticas de POO e os princípios do SOLID.

### 📦 `provided/interfaces/`
Declare aqui as **interfaces providas pelo seu componente**, ou seja, os serviços que ele disponibiliza para outros componentes ou sistemas.

### 📦 `required/interfaces/`
Declare aqui as **interfaces requeridas**, ou seja, os serviços externos dos quais seu componente depende.

---

## Dependência

Este template depende do pacote de **abstrações do PIC** disponível em:

📦 Repositório: [github.com/lifveras/bradeco_pic_abstract](https://github.com/lifveras/bradeco_pic_abstract)

A seguinte dependência já está no `pom.xml`:

```xml
<dependency>
    <groupId>io.github.lifveras</groupId>
    <artifactId>bradeco_pic_abstract</artifactId>
    <version>1.0.0</version>
</dependency>
````

---

## Comece a desenvolver

1. Substitua `MyComponentProvidedInterface` por sua interface provida.
2. Substitua `MyComponentRequiredInterface` por sua interface requerida (se houver).
3. Substitua `MyComponentConcreteInterface` pela interface concreta do seu componente.
4. Substitua `MyComponentConcreteInterfacePort` pelo porto concreto do seu componente.
5. Implemente as classes do componente dentro de `internal/`, aplicando boas práticas de design de código (SOLID)

---

## Sobre o autor do padrão

O padrão utilizado neste projeto é uma abstração inspirada no modelo proposto pelo Prof. Ricardo Silva.

📘 Livro: [Desenvolvimento Orientado a Componentes com UML](https://www.amazon.com.br/Desenvolvimento-orientado-componentes-com-UML-ebook/dp/B07DYDSMCZ/ref=sr_1_1)

📺 Canal do YouTube - Parte Teórica: [Parte Teórica](https://www.youtube.com/watch?v=0BmWe7d17NU&list=PLQb3t1uw-rpFIPbyWZCfOc9CTN5chPa0d)

📺 Canal do YouTube [Parte Prática](https://www.youtube.com/watch?v=4mnZnNAYHKc&list=PLQb3t1uw-rpHXs0N674qsdYB_Dlgoriy3)

---

## Autor do Template

* Luiz Gustavo Véras
* [gustavo\_veras@ifsp.edu.br](mailto:gustavo_veras@ifsp.edu.br)
* [@lifveras no GitHub](https://github.com/lifveras)

