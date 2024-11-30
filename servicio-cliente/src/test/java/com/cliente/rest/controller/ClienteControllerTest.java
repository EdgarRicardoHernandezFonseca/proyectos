package com.cliente.rest.controller;

import com.cliente.rest.controller.ClienteController;
import com.cliente.rest.model.Cliente;
import com.cliente.rest.service.ClienteService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(ClienteController.class)
class ClienteControllerTest {
	 @Autowired
	    private MockMvc mockMvc;

	    @MockBean
	    private ClienteService clienteService;

	    @Test
	    void obtenerCliente_ClienteMockeado() throws Exception {
	        Cliente cliente = new Cliente("Edgar", "Ricardo", "Hernandez", "Fonseca", "3163390133", "Calle 5 #6-95", "Bogotá");
	        when(clienteService.obtenerCliente("C", "23445322")).thenReturn(cliente);

	        mockMvc.perform(get("/api/v1/clientes/C/23445322")
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.responseStatus").value(200))
	                .andExpect(jsonPath("$.responseBody.primerNombre").value("Edgar"))
	                .andExpect(jsonPath("$.responseBody.ciudadResidencia").value("Bogotá"));
	    }

	    @Test
	    void obtenerCliente_ClienteNoMockeado() throws Exception {
	        when(clienteService.obtenerCliente("C", "11111111")).thenReturn(null);

	        mockMvc.perform(get("/api/v1/clientes/C/11111111")
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isNotFound());
	    }

	    @Test
	    void obtenerCliente_TipoDocumentoInvalido() throws Exception {
	        mockMvc.perform(get("/api/v1/clientes/X/23445322")
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isBadRequest())
	                .andExpect(jsonPath("$.error").value(400))
	                .andExpect(jsonPath("$.mensaje").value("Tipo de documento inválido: X"));
	    }

	    @Test
	    void obtenerCliente_ClienteNoEncontrado() throws Exception {
	        when(clienteService.obtenerCliente("C", "11111111")).thenReturn(null);

	        mockMvc.perform(get("/api/v1/clientes/C/11111111")
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isNotFound())
	                .andExpect(jsonPath("$.error").value(404))
	                .andExpect(jsonPath("$.mensaje").value("Cliente no encontrado: tipoDocumento=C numeroDocumento=11111111"));
	    }

	    @Test
	    void obtenerCliente_ErrorInterno() throws Exception {
	        when(clienteService.obtenerCliente("C", "12345678")).thenThrow(new RuntimeException("Error interno"));

	        mockMvc.perform(get("/api/v1/clientes/C/12345678")
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isInternalServerError());
	    }
}
