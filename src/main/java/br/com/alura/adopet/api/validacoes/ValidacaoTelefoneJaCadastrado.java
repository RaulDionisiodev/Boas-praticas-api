package br.com.alura.adopet.api.validacoes;

import br.com.alura.adopet.api.dto.AbrigoDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidacaoTelefoneJaCadastrado implements ValidacaoCadastrarAbrigo{

    @Autowired
    private AbrigoRepository abrigoRepository;

    @Override
    public void validar(AbrigoDto dto) {
        boolean telefoneJaCadastrado = abrigoRepository.existsByTelefone(dto.telefone());;

        if (telefoneJaCadastrado) {
            throw new ValidacaoException("Telefone Já cadastrado para outro abrigo!");
        }
    }
}
