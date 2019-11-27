package com.metrica.formacion.metricahorariosservice.exceptionHandler;

import lombok.Data;
<<<<<<< HEAD
=======
import lombok.NoArgsConstructor;
>>>>>>> origin/controller
import org.springframework.http.HttpStatus;

@Data
public class CustomErrorResponse extends RuntimeException {

<<<<<<< HEAD
	private String exMessage;
	private HttpStatus status;

	public CustomErrorResponse(Class cla, String mensaje, String exmensaje, HttpStatus statusCode) {
		super("Class name: " + cla.getSimpleName()+", "+mensaje);
		this.exMessage = exmensaje;
		status = statusCode;
=======
	private HttpStatus status;
	private String exMessage;

	public CustomErrorResponse(Class cla, String mensaje, String exmensaje) {
		super("Class name: " + cla.getSimpleName()+", "+mensaje);
		this.exMessage = exmensaje;
	}

	public CustomErrorResponse(Class cla, String mensaje, String exmensaje,HttpStatus status) {
		super("Class name: " + cla.getSimpleName()+", "+mensaje);
		this.exMessage = exmensaje;
		this.status = status;
>>>>>>> origin/controller
	}
}
