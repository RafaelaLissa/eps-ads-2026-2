package br.edu.fatecfranca.api.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserRequest(
    @NotBlank(message = "Nome completo obrigatório.")
    String fullname,

    @NotBlank(message = "Nome de usuário obrigatório.")
    @Pattern(
        regexp = "^[a-zA-Z0-9._-]+$",
        message = "Nome de usuário obrigatório, contendo apenas letras sem acentos, números, ponto, hífen e sublinhado."
    )
    String username,

    @NotBlank(message = "E-mail obrigatório.")
    @Email(message = "E-mail com formato válido.")
    String email,

    @NotBlank(message = "Senha obrigatória.")
    @Size(min = 8, message = "Senha obrigatória, com pelo menos 8 caracteres.")
    String password,

    @NotNull(message = "Deve informar true ou false.")
    Boolean isAdmin
) {}