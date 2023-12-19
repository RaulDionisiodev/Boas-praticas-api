package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.TutorDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TutorService {

    @Autowired
    private TutorRepository repository;

    public void cadastrar(TutorDto dto) {
        boolean telefoneJaCadastrado = repository.existsByTelefone(dto.telefone());
        boolean emailJaCadastrado = repository.existsByEmail(dto.email());

        if (telefoneJaCadastrado || emailJaCadastrado) {
           throw new ValidacaoException("Dados já cadastrados para outro tutor!");
        } else {
            Tutor tutor = new Tutor(dto.nome(), dto.telefone(), dto.email());
            repository.save(tutor);
        }
    }

    public void save(TutorDto dto) {
        Tutor tutor = new Tutor(dto.nome(), dto.telefone(), dto.email());
        repository.save(tutor);
    }
}
