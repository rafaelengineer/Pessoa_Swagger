package com.accenture.pessoa_swagger.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.pessoa_swagger.entity.Pessoa;
import com.accenture.pessoa_swagger.repository.PessoaRepository;
import com.accenture.pessoa_swagger.sevice.PessoaService;

@RestController
public class PessoaController {
    @Autowired
    private PessoaService _pessoaService;

    /**
     * @return
     */
    @GetMapping("/pessoas")
	public List<Pessoa> getAllPessoas() 
	{
		return _pessoaService.GetAllPeople();
	}

    @RequestMapping(value = "/pessoa/{id}", method = RequestMethod.GET)
    public ResponseEntity<Pessoa> GetById(@PathVariable(value = "id") long id)
    {
        return _pessoaService.GetById(id);
    }

    @RequestMapping(value = "/pessoa", method =  RequestMethod.POST)
    public Pessoa Post(@Valid @RequestBody Pessoa pessoa)
    {
        return _pessoaService.Save(pessoa);
    }
    
    @RequestMapping(value = "/pessoa/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<Pessoa> Put(@PathVariable(value = "id") long id, @Valid @RequestBody Pessoa newPerson)
    {
       return _pessoaService.SeekNReplace(id, newPerson);
    }
    
    @RequestMapping(value = "/pessoa/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> Delete(@PathVariable(value = "id") long id)
    {
        return _pessoaService.Delete(id);
    }
}
