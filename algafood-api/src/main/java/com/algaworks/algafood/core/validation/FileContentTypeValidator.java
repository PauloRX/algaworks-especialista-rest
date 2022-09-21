package com.algaworks.algafood.core.validation;

import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.web.multipart.MultipartFile;

public class FileContentTypeValidator implements ConstraintValidator<FileContentType, MultipartFile> {

	private List<String> allowedMediaType;
	
	@Override
	public void initialize(FileContentType constraintAnnotation) {
		this.allowedMediaType = Arrays.asList(constraintAnnotation.allowed());
	}
	
	@Override
	public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext context) {
		return multipartFile == null || this.allowedMediaType.contains(multipartFile.getContentType());
	}

}
