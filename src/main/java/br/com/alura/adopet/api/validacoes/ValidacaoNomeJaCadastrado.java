package br.com.alura.adopet.api.validacoes;

import br.com.alura.adopet.api.dto.AbrigoDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidacaoNomeJaCadastrado implements ValidacaoCadastrarAbrigo{

    @Autowired
    private AbrigoRepository abrigoRepository;

    @Override
    public void validar(AbrigoDto dto) {
        boolean nomeJaCadastrado = abrigoRepository.existsByNome(dto.nome());

        if (nomeJaCadastrado) {
            throw new ValidacaoException("Nome Já cadastrado para outro abrigo!");
        }
    }
}
