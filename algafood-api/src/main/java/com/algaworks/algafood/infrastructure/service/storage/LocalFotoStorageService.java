package com.algaworks.algafood.infrastructure.service.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import com.algaworks.algafood.domain.service.FotoStorageService;

@Service
public class LocalFotoStorageService implements FotoStorageService {

	@Value("${algafood.storage.local.diretorio-fotos}")
	private Path photoDirectory;
	
	@Override
	public void armazenar(NovaFoto novafoto) {
		
		
		try {
			Path arquivoPath = getArquivoPath(novafoto.getFilename());
			FileCopyUtils.copy(novafoto.getInputStream(), Files.newOutputStream(arquivoPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private Path getArquivoPath(String filename) {
		return photoDirectory.resolve(filename);
	}

}
