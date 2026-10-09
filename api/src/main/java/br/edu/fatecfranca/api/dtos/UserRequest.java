package br.edu.fatecfranca.api.dtos;

public record UserRequest(
    String fullName,
    String username,
    String email,
    String password,
    Boolean isAdmin
) {}
