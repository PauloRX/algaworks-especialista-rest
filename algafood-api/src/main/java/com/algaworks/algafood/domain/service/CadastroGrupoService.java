package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.GrupoNaoEncontradoException;
import com.algaworks.algafood.domain.model.Grupo;
import com.algaworks.algafood.domain.repository.GrupoRepository;

@Service
public class CadastroGrupoService {

	private static final String MSG_GRUPO_EM_USO  = "Grupo de código %d não pode ser removido, pois está em uso";
	
	@Autowired
	private GrupoRepository repository;
	
	public Grupo buscarOuFalhar(Long grupoId) {
		return repository.findById(grupoId).orElseThrow(() -> new GrupoNaoEncontradoException(grupoId));
	}

	@Transactional
	public Grupo salvar(Grupo grupo) {
		return repository.save(grupo);
	}

	@Transactional
	public void excluir(Long grupoId) {
		try {
			repository.deleteById(grupoId);
			repository.flush();
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(grupoId);
		} catch (EmptyResultDataAccessException e) {
			throw new GrupoNaoEncontradoException(String.format(MSG_GRUPO_EM_USO, grupoId));
		}
	}
	
}
