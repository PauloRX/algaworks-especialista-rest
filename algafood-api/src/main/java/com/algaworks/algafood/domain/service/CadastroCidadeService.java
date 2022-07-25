package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.repository.EstadoRepository;

@Service
public class CadastroCidadeService {

	@Autowired
	CidadeRepository cidadeRepository;
	
	@Autowired
	EstadoRepository estadoRepository;
	
	public Cidade salvar(Cidade cidade) {
		Long estadoId = cidade.getEstado().getId();
		Estado estado = estadoRepository.buscarPorId(estadoId);
		if (estado == null) {
			throw new EntidadeNaoEncontradaException(String.format("O Estado codigo %d nao foi encontrado", estadoId));
		}
		cidade.setEstado(estado);
		return cidadeRepository.salvar(cidade);
	}

	public void excluir(Long cidadeId) {
		Cidade cidade = cidadeRepository.buscarPorId(cidadeId);
		if (cidade == null) {
			throw new EntidadeNaoEncontradaException(String.format("A Cidade codigo %d nao foi localizada", cidadeId));
		}
		
	}
	
}
