package com.algaworks.algafood.domain.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.exception.UsuarioNaoEncontradoException;
import com.algaworks.algafood.domain.model.Grupo;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.repository.UsuarioRepository;

@Service
public class CadastroUsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private CadastroGrupoService cadastroGrupo;
	
	public Usuario buscarOuFalhar(Long usuarioId) {
		return usuarioRepository.findById(usuarioId).orElseThrow(
				() -> new UsuarioNaoEncontradoException(usuarioId));
	}

	@Transactional
	public Usuario salvar(Usuario usuario) {
		
		usuarioRepository.detach(usuario);
		
		Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(usuario.getEmail());
		if (usuarioExistente.isPresent() && !usuarioExistente.get().equals(usuario)) {
			throw new NegocioException(
					String.format("Ja existe um usuario cadastro com o e-mail %s", usuario.getEmail()));
		}
	
		return usuarioRepository.save(usuario);
	
	}
	
	public void alterarSenha(Long usuarioId, String senhaAtual, String novaSenha) {
		Usuario usuario = this.buscarOuFalhar(usuarioId);
		
		if (usuario.senhaNaoCoincideCom(senhaAtual)) {
			throw new NegocioException("A senha informada nao coincide com a senha atual");
		}
		
		usuario.setSenha(novaSenha);
		
	}
	
	@Transactional
	public void desassociar(Long usuarioId, Long grupoId) {
		Usuario usuario = buscarOuFalhar(usuarioId);
		Grupo grupo = cadastroGrupo.buscarOuFalhar(grupoId);
		usuario.desassociarGrupo(grupo);
	}
	
	@Transactional
	public void associar(Long usuarioId, Long grupoId) {
		Usuario usuario = buscarOuFalhar(usuarioId);
		Grupo grupo = cadastroGrupo.buscarOuFalhar(grupoId);
		usuario.associarGrupo(grupo);
	}

}
