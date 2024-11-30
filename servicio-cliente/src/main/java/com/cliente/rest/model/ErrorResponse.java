package com.cliente.rest.model;

public class ErrorResponse {
	
	private int error;
    private String mensaje;

    public ErrorResponse(int error, String mensaje) {
        this.error = error;
        this.mensaje = mensaje;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}