package com.cliente.rest.service;

import com.cliente.rest.model.Cliente;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ClienteServiceTest {
	
	private final ClienteService clienteService = new ClienteService();

    @Test
    void obtenerCliente_ClienteExistente() {
        Cliente cliente = clienteService.obtenerCliente("C", "23445322");
        assertNotNull(cliente, "El cliente debería existir");
        assertEquals("Edgar", cliente.getPrimerNombre());
        assertEquals("Hernandez", cliente.getPrimerApellido());
    }

    @Test
    void obtenerCliente_ClienteNoExistente() {
        Cliente cliente = clienteService.obtenerCliente("C", "00000000");
        assertNull(cliente, "El cliente no debería existir");
    }

}
