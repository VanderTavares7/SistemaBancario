# 🏦 Sistema Bancário - Spring Boot

Este projeto foi desenvolvido com o objetivo de **praticar e consolidar conhecimentos em desenvolvimento backend**, simulando um sistema bancário real, aplicando regras de negócio, segurança e organização de código.

A aplicação é totalmente testada através do **Postman**, permitindo a validação das rotas e fluxos do sistema.

## 🎯 Objetivo do Projeto

- Aprimorar habilidades em Java e Spring Boot
- Trabalhar regras de negócio do mundo real
- Praticar autenticação e autorização com Spring Security
- Criar uma API REST organizada e escalável
- Construir um projeto sólido para portfólio profissional

## 🚀 Funcionalidades

- Cadastro de usuários com:
  - Nome
  - CPF
  - Idade
  - Saldo
  - Limite de crédito
- Regras de negócio:
  - Usuários menores de 18 anos não podem solicitar cartão de crédito
  - Compras no débito apenas se houver saldo suficiente
  - Compras no crédito com parcelamento em até 12x
  - Produtos até R$ 1.000 podem ser parcelados em até 6x
  - Produtos acima de R$ 1.000 podem ser parcelados em até 12x
- Depósito e débito em conta
- Solicitação e aumento de limite de crédito
- Autenticação e autorização com Spring Security
- Criação de roles (USER, ADMIN)
- Validação de usuário existente via CPF

## 🔐 Segurança

- Spring Security
- Controle de acesso por roles
- Endpoints protegidos

## 🧪 Testes da API

- Todos os endpoints são testados utilizando o **Postman**
- Collection do Postman disponível no projeto (ou pode ser adicionada futuramente)

## 🛠️ Tecnologias Utilizadas

- Java
- Spring Boot
- Spring Security
- JPA / Hibernate
- Banco de dados relacional
- Postman
