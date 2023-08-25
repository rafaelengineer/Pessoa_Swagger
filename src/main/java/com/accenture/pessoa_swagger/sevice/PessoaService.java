package com.accenture.pessoa_swagger.sevice;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

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

    public Pessoa Save(Pessoa pessoa)
    {
        return _pessoaRepository.save(pessoa);
    }
    public ResponseEntity<Pessoa> SeekNReplace(long id, Pessoa newPerson)
    {
        Optional<Pessoa> oldPessoa = _pessoaRepository.findById(id);
        if(oldPessoa.isPresent()){
            Pessoa pessoa = oldPessoa.get();
            pessoa.setNome(newPerson.getNome());
            _pessoaRepository.save(pessoa);
            return new ResponseEntity<Pessoa>(newPerson, HttpStatus.OK);
        }
        else
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Object> Delete(@PathVariable(value = "id") long id)
    {
        Optional<Pessoa> pessoa = _pessoaRepository.findById(id);
        if(pessoa.isPresent()){
            _pessoaRepository.delete(pessoa.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}