package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.TutorDto;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.TutorRepository;
import br.com.alura.adopet.api.validacoes.ValidacaoCadastrarTutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TutorService {

    @Autowired
    private TutorRepository repository;

    @Autowired
    private List<ValidacaoCadastrarTutor> validacaoCadastrarTutorList;

    public void cadastrar(TutorDto dto) {

        validacaoCadastrarTutorList.forEach(
            validacaoCadastrarTutor -> validacaoCadastrarTutor.validar(dto)
        );

        Tutor tutor = new Tutor(dto.nome(), dto.telefone(), dto.email());
        repository.save(tutor);
    }

    public void save(TutorDto dto) {
        Tutor tutor = new Tutor(dto.nome(), dto.telefone(), dto.email());
        repository.save(tutor);
    }
}
