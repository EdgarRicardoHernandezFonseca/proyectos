package com.cliente.rest.service;

import org.springframework.stereotype.Service;
import com.cliente.rest.model.Cliente;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ClienteService {

	private static final Logger logger = LoggerFactory.getLogger(ClienteService.class);

	public Cliente obtenerCliente(String tipoDocumento, String numeroDocumento) {
	        
		logger.debug("Buscando cliente con tipoDocumento={} y numeroDocumento={}", tipoDocumento, numeroDocumento);

	   // Validar que el cliente corresponde al único mock permitido
	   if ("C".equals(tipoDocumento) && "23445322".equals(numeroDocumento)) {
	        Cliente cliente = new Cliente(
	                "Edgar", "Ricardo", "Hernandez", "Fonseca",
	                "3163390133", "Calle 5 #6-95", "Bogotá"
	        );
	        logger.info("Cliente encontrado: {} {}", cliente.getPrimerNombre(), cliente.getPrimerApellido());
	            return cliente;
	        }

	        logger.warn("Cliente no encontrado para tipoDocumento={} y numeroDocumento={}", tipoDocumento, numeroDocumento);
	        return null;
	  	}
}
