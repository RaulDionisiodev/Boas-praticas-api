package br.com.alura.adopet.api.dto;

import jakarta.validation.constraints.NotBlank;

public record CadastrarPetDto(@NotBlank Long id) {
}
