package com.projetojpa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetojpa.entity.Formativa;
import com.projetojpa.repository.FormativaRepository;

@Service
public class FormativaService {
	private FormativaRepository formaRepository;
	
	@Autowired
	public FormativaService(FormativaRepository formaRepository) {
		this.formaRepository = formaRepository;
	}
	
	public List<Formativa> buscarTodos() {
		return formaRepository.findAll();
	}
	
	public Formativa buscarId(Long id) {
		Optional<Formativa> forma = formaRepository.findById(id);
		return forma.orElse(null);
	}
	
	public Formativa salvar(Formativa forma) {
		return formaRepository.save(forma);
	}
	
	public Formativa alterar(Long id, Formativa alterarForma) {
		Optional<Formativa> existe = formaRepository.findById(id);
		if (existe.isPresent()) {
			alterarForma.setId(id);
			return formaRepository.save(alterarForma);
		}
		return null;
	}
	
	public boolean apagar(Long id) {
		Optional<Formativa> existe = formaRepository.findById(id);
		if (existe.isPresent()) {
			formaRepository.deleteById(id);
			return true;
		}
		return false;
	}
}
