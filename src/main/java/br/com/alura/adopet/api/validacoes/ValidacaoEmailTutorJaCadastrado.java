package br.com.alura.adopet.api.validacoes;


import br.com.alura.adopet.api.dto.TutorDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidacaoEmailTutorJaCadastrado implements ValidacaoCadastrarTutor{

    @Autowired
    private TutorRepository repository;

    @Override
    public void validar(TutorDto dto) {
        boolean emailJaCadastrado = repository.existsByEmail(dto.email());

        if (emailJaCadastrado) {
            throw new ValidacaoException("Email Já cadastrado para outro Tutor!");
        }
    }
}
