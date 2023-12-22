package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.PetDto;
import br.com.alura.adopet.api.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class PetService {

    @Autowired
    private PetRepository repository;

    public List<PetDto> listarTodosDisponiveis() {
        List<PetDto> petDtos = new ArrayList<>();
        repository.findAllBYAdotado(true).forEach(
            pet -> {
               petDtos.add(pet.toDto());
            }
        );
        return petDtos;
    }
}
