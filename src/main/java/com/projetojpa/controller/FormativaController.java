package com.projetojpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetojpa.entity.Formativa;
import com.projetojpa.service.FormativaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name= "formativa", description = "API REST DA AVALIAÇÃO FORMATIVA")
@RestController
@RequestMapping("/formativa")
public class FormativaController {

	private final FormativaService formaService;
	
	@Autowired
	public FormativaController(FormativaService formaService) {
		this.formaService = formaService;
	}
	
	@GetMapping("/(id)")
	@Operation(summary = "Localiza produto pelo id")
	public ResponseEntity<Formativa> buscarId(@PathVariable Long id) {
		Formativa forma = formaService.buscarId(id);
		if(forma != null) {
			return ResponseEntity.ok(forma);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	@GetMapping
	@Operation(summary = "Apresenta todos os produtos")
	public ResponseEntity<List<Formativa>> buscarTodos(){
		List<Formativa> formas = formaService.buscarTodos();
		return ResponseEntity.ok(formas);
	}
	
	
	@PostMapping
	@Operation(summary = "Inserindo dados")
	public ResponseEntity<Formativa> salvar(@RequestBody @Valid Formativa forma){
		Formativa salvar = formaService.salvar(forma);
		return ResponseEntity.status(HttpStatus.CREATED).body(salvar);
	}
	
	@PutMapping
	@Operation(summary = "Alterando dados")
	public ResponseEntity<Formativa> alterar(@PathVariable Long id, @RequestBody @Valid Formativa forma){
		Formativa alterar = formaService.alterar(id, forma);
		if(alterar !=null) {
			return ResponseEntity.ok(forma);
		}
		else {
			return ResponseEntity.notFound().build();
		}
		
	}
	@DeleteMapping("/(id)")
	@Operation(summary = "Deletando dados")
	public ResponseEntity<Formativa> apagar(@PathVariable Long id){
		boolean apagar = formaService.apagar(id);
		if(apagar) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
}
