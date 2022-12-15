package com.sample.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sample.api.model.Aluno;
import com.sample.api.service.AlunoService;


@RestController
@RequestMapping("/aluno")
public class AlunoController {
	
	
	@Autowired 	/* Faz a injeção de dependência, tornando desnecessário  */
	AlunoService service;
	
	@GetMapping(value= "/listar")      /*faz o mapeamento da URL e encontra os métodos*/
    @ResponseBody                      	/*Retorna um corpo com reposta à requisição feita, neste caso, como resposta vou retornar os alunos cadastrados*/
    public ResponseEntity<List<Aluno>> listar(){
    	List<Aluno> alunos = service.listar();   /*por meio do método "findAll" do JpaRepository executa a consultar no banco de dados*/	
    	return new ResponseEntity<List<Aluno>>(alunos, HttpStatus.OK);	/*Retorna a lista em JSON*/
    }
	
	 @GetMapping(value= "/buscar") 
	    @ResponseBody 
	    public ResponseEntity<Aluno> buscarPorId(@RequestParam(name = "id") Long id){   /*recebe os parâmetros para realizar a consulta*/	
		Aluno aluno = service.buscarPorId(id);		/*executa a consulta no banco de dados*/
	    return new ResponseEntity<Aluno>(aluno, HttpStatus.OK);		/*Retorna em JSON a entidade específica pesquesida */    	
	    }
	    
	
	@PostMapping(value= "/novo")      																
	@ResponseBody                    																 
    public ResponseEntity<Aluno> salvar(@RequestBody Aluno aluno){ 	/*Recebe os dados para persistir no banco de dados*/
    	Aluno aln = service.salvar(aluno);    /*por meio do método "findAll" do JpaRepository executa a consultar no banco de dados*/
    	return new ResponseEntity<Aluno>(aln, HttpStatus.CREATED);		/*Retorna a lista em JSON do objeto criado "aln" e o HttpStatus*/
    }
	

	@PutMapping(value = "/atualizar")
    @ResponseBody
    public ResponseEntity<?> atualizar(@RequestBody Aluno aluno){
    	Aluno aln = service.salvar(aluno);
    	return new ResponseEntity<Aluno>(aln, HttpStatus.OK);	 /*Retorna a lista em JSON do objeto atualizado "aln" e o HttpStatus*/
    }
	
	
	@DeleteMapping(value = "/deletar")
	@ResponseBody
	public ResponseEntity<?> deletar(@RequestParam Long id){
		service.deletar(id);
		return new ResponseEntity<String>("Deletado com sucesso", HttpStatus.ACCEPTED);	/*Retorna a mensagem do objeto deletado do banco por meio do "id" e o HttpStatus*/
	}
	
}
