package com.metrica.formacion.metricahorariosservice.exceptionHandler;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class CustomErrorResponse extends RuntimeException {

	private String exMessage;
	private HttpStatus status;

	public CustomErrorResponse(Class cla, String mensaje, String exmensaje) {
		super("Class name: " + cla.getSimpleName()+", "+mensaje);
		this.exMessage = exmensaje;
	}

	public CustomErrorResponse(Class cla, String mensaje, String exmensaje,HttpStatus status) {
		super("Class name: " + cla.getSimpleName()+", "+mensaje);
		this.exMessage = exmensaje;
		this.status = status;
	}
}
