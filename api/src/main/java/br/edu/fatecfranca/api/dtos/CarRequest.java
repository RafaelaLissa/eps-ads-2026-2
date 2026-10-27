package br.edu.fatecfranca.api.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public record CarRequest(
    @NotBlank(message = "Marca obrigatória.")
    String brand,

    @NotBlank(message = "Modelo obrigatório.")
    String model,

    @NotBlank(message = "Cor obrigatória.")
    String color,

    @NotNull(message = "Ano obrigatório.")
    @Positive(message = "Ano deve ser maior que zero.")
    Integer yearManufacture,

    @NotNull(message = "Deve informar true ou false.")
    Boolean imported,

    @NotBlank(message = "Placa obrigatória.")
    @Pattern(
        regexp = "^[A-Z]{3}-[0-9]{4}$|^[A-Z]{3}[0-9][A-Z][0-9]{2}$",
        message = "Placa obrigatória, no formato ABC-1234 ou ABC1D23."
    )
    String plates,

    @PastOrPresent(message = "Quando informada, não pode estar no futuro.")
    LocalDate sellingDate,

    @Positive(message = "Quando informado, deve ser maior que zero.")
    @Digits(integer = 10, fraction = 2, message = "Deve conter até 10 dígitos inteiros e 2 decimais.")
    BigDecimal sellingPrice,

    @Positive(message = "Quando informado, deve ser maior que zero.")
    Long customerId
) {}