package com.algaworks.algafood.di.notificacao;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("notificador.email")
public class NotificadorProperties {

	/**
	 * Host do servidor SMTP
	 */
	private String hostServer;
	/**
	 *  Porta do servidor SMTP
	 */
	private Integer portServer;

	public String getHostServer() {
		return hostServer;
	}

	public void setHostServer(String hostServer) {
		this.hostServer = hostServer;
	}

	public Integer getPortServer() {
		return portServer;
	}

	public void setPortServer(Integer portServer) {
		this.portServer = portServer;
	}

}
