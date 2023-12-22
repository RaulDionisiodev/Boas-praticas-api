package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.AbrigoDto;
import br.com.alura.adopet.api.dto.CadastrarPetDto;
import br.com.alura.adopet.api.dto.PetDto;
import br.com.alura.adopet.api.exception.CadastroException;
import br.com.alura.adopet.api.exception.PetNotFoundException;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import br.com.alura.adopet.api.validacoes.ValidacaoCadastrarAbrigo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AbrigoService {

    @Autowired
    private AbrigoRepository repository;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private List<ValidacaoCadastrarAbrigo> validacaoCadastrarAbrigoList;

    public List<AbrigoDto> listar() {
        List<AbrigoDto> abrigoDtos = new ArrayList<>();
         repository.findAll().forEach(
             abrigo -> {
                 abrigoDtos.add(abrigo.toDto());
             }
         );
         return abrigoDtos;
    }

    public void cadastrar(AbrigoDto dto) {

        validacaoCadastrarAbrigoList.forEach(v -> v.validar(dto));

        Abrigo abrigo = new Abrigo(dto.nome(), dto.telefone(), dto.email());

        repository.save(abrigo);
    }

    public List<PetDto> listarPets(String idOuNome) {
        try {
            Long id = Long.parseLong(idOuNome);
            List<Pet> pets = repository.getReferenceById(id).getPets();
            List<PetDto> petDtos = new ArrayList<>();
            pets.forEach(pet -> petDtos.add(pet.toDto()));
            return petDtos;
        } catch (NumberFormatException e) {
            try {
                List<Pet> pets = repository.findByNome(idOuNome).getPets();
                List<PetDto> petDtos = new ArrayList<>();
                pets.forEach(pet -> petDtos.add(pet.toDto()));
                return petDtos;
            } catch (EntityNotFoundException enfe) {
                throw new  PetNotFoundException("Pet Não encontrado");
            }
        }
    }

    public void cadastrarPet(String idOuNome, CadastrarPetDto dto) {
        try {
            Long id = Long.parseLong(idOuNome);
            Abrigo abrigo = repository.getReferenceById(id);
            Pet pet = petRepository.getReferenceById(dto.id());
            pet.setAbrigo(abrigo);
            pet.setAdotado(false);
            abrigo.getPets().add(pet);
            repository.save(abrigo);
        } catch (NumberFormatException nfe) {
            try {
                Abrigo abrigo = repository.findByNome(idOuNome);
                Pet pet = petRepository.getReferenceById(dto.id());
                pet.setAbrigo(abrigo);
                pet.setAdotado(false);
                abrigo.getPets().add(pet);
                repository.save(abrigo);
            } catch (EntityNotFoundException enfe) {

               throw new  CadastroException("Não foi possível realizar o cadastro");
            }

        }
    }

}
