package com.sample.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sample.api.model.Aluno;
import com.sample.api.repository.AlunoRepository;


@Service
public class AlunoService {


	@Autowired 	/* Faz a injeção de dependência, tornando desnecessário  */
	AlunoRepository repository;



	public List<Aluno> listar(){
		return repository.findAll();   
	}


	public Aluno buscarPorId(Long id){   
		return repository.findById(id).get();			 	
	}


	public Aluno salvar(Aluno aluno){ 	
		return repository.save(aluno);    
	}


	public Aluno atualizar(Aluno aluno){
		if(aluno.getId() == null) {	
			new ResponseEntity<String>("Id não foi informado", HttpStatus.BAD_REQUEST);           
		}	
		return  repository.saveAndFlush(aluno);		
	}


	public void deletar(Long id){
		repository.deleteById(id);	
	}	
}
