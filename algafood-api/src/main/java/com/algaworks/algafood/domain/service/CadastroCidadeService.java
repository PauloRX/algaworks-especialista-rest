package com.algaworks.algafood.domain.service;

import java.util.Optional;

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
		Optional<Estado> estado = estadoRepository.findById(estadoId);
		if (estado.isEmpty()) {
			throw new EntidadeNaoEncontradaException(String.format("O Estado codigo %d nao foi encontrado", estadoId));
		}
		cidade.setEstado(estado.get());
		return cidadeRepository.save(cidade);
	}

	public void excluir(Long cidadeId) {
		Optional<Cidade> cidade = cidadeRepository.findById(cidadeId);
		if (cidade.isEmpty()) {
			throw new EntidadeNaoEncontradaException(String.format("A Cidade codigo %d nao foi localizada", cidadeId));
		}
		cidadeRepository.deleteById(cidadeId);
	}
	
}
