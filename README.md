# Teste Prático — Iniflex

<p align="center">
  <img src="https://img.shields.io/badge/Java-8-ED8B00?style=for-the-badge&logo=java&logoColor=white" alt="Java 8">
  <img src="https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white" alt="Maven">
  <img src="https://img.shields.io/badge/Lombok-BC4521?style=for-the-badge&logo=lombok&logoColor=white" alt="Lombok">
  <img src="https://img.shields.io/badge/Status-Concluído-success?style=for-the-badge" alt="Status">
</p>

## Sobre o projeto

Este projeto foi desenvolvido como solução para o **Teste Prático de Programação da Iniflex**, proposto durante o processo seletivo da **Prothera Tecnologia**.

O objetivo do desafio é desenvolver uma aplicação Java capaz de cadastrar, manipular, organizar e consultar informações de funcionários, utilizando conceitos fundamentais da linguagem Java e da programação orientada a objetos.

O projeto foi desenvolvido priorizando **código limpo, organização, legibilidade e boas práticas de programação**.

---

## Tecnologias utilizadas

- ☕ **Java 8**
- 📦 **Maven**
- ♻️ **Lombok**
- 📋 **Collections**
- 🔄 **Stream API**
- 💰 **BigDecimal**
- 📅 **LocalDate**
- 🧪 **JUnit**

---

## Funcionalidades

O projeto implementa todos os requisitos propostos no teste.

### Cadastro de funcionários

Os funcionários são cadastrados em uma `List<Funcionario>`, respeitando a ordem e os dados fornecidos no desafio.

Cada funcionário possui:

- Nome
- Data de nascimento
- Salário
- Função

---

### Remoção de funcionário

O funcionário **João** é localizado e removido da lista.

```java
funcionarios.removeIf(funcionario ->
        funcionario.getNome().equalsIgnoreCase("João")
);
