package br.com.alura.adopet.api.validacoes;

import br.com.alura.adopet.api.dto.TutorDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidacaoTelefonelTutorJaCadastrado implements ValidacaoCadastrarTutor{

    @Autowired
    private TutorRepository repository;

    @Override
    public void validar(TutorDto dto) {
        boolean telefoneJaCadastrado = repository.existsByTelefone(dto.telefone());

        if (telefoneJaCadastrado) {
            throw new ValidacaoException("Telefone Já cadastrado para outro Tutor!");
        }
    }
}
