package br.com.alura.adopet.api.validacoes;

import br.com.alura.adopet.api.dto.AbrigoDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidacaoEmailJaCadastrado implements ValidacaoCadastrarAbrigo{

    @Autowired
    private AbrigoRepository repository;

    @Override
    public void validar(AbrigoDto dto) {
        boolean emailJaCadastrado = repository.existsByEmail(dto.email());

        if (emailJaCadastrado) {
            throw new ValidacaoException("Email Já cadastrado para outro abrigo!");
        }
    }
}
