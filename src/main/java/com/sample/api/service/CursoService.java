package com.sample.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.api.model.Curso;
import com.sample.api.repository.CursoRepository;


@RestController
@RequestMapping("/curso")
public class CursoService {
	
	
	@Autowired 	/* Faz a injeção de dependência, tornando desnecessário  */
	CursoRepository repository;



	public List<Curso> listar(){
		return repository.findAll();   
	}


	public Curso buscarPorId(Long id){   
		return repository.findById(id).get();			 	
	}


	public Curso salvar(Curso curso){ 	
		return repository.save(curso);    
	}


	public Curso atualizar(Curso curso){
		if(curso.getId() == null) {	
			new ResponseEntity<String>("Id não foi informado", HttpStatus.BAD_REQUEST);           
		}	
		return  repository.saveAndFlush(curso);		
	}


	public void deletar(Long id){
		repository.deleteById(id);	
	}	
}

