package com.accenture.pessoa_swagger.sevice;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.Valid;

import com.accenture.pessoa_swagger.entity.Pessoa;
import com.accenture.pessoa_swagger.repository.PessoaRepository;
//Definindo as regras de negócio.
@Service
public class PessoaService{
    @Autowired
    private PessoaRepository _pessoaRepository;
    //Obtendo todos os registros de pessoas
    public List<Pessoa> GetAllPeople() {
       
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		_pessoaRepository.findAll().forEach(person -> pessoas.add(person));
		return pessoas;
    }

    public ResponseEntity<Pessoa> GetById(long id)
    {
        Optional<Pessoa> pessoa = _pessoaRepository.findById(id);
        if(pessoa.isPresent())
            return new ResponseEntity<Pessoa>(pessoa.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    

}