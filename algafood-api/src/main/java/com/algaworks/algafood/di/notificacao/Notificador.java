package com.algaworks.algafood.di.notificacao;

import com.algaworks.algafood.di.model.Cliente;

public interface Notificador {

	void notificar(Cliente cliente, String mensagem);

}