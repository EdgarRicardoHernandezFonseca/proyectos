package com.cliente.rest.controller;

import com.cliente.rest.model.Cliente;
import com.cliente.rest.model.ErrorResponse;
import com.cliente.rest.model.SuccessResponse;
import com.cliente.rest.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1/clientes")
@Api(tags = "Gestión de Clientes", description = "Servicio REST que me permita consultar la información básica de un cliente.")
public class ClienteController {

	private static final Logger logger = LoggerFactory.getLogger(ClienteController.class);

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @ApiOperation("Obtiene la información de un cliente por tipo y número de documento")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Cliente encontrado", response = SuccessResponse.class, examples = @io.swagger.annotations.Example(value = {
            @io.swagger.annotations.ExampleProperty(mediaType = "application/json", value = "{\n  \"ResponseStatus\": 200,\n  \"ResponseBody\": {\n    \"primerNombre\": \"Edgar\",\n    \"segundoNombre\": \"Ricardo\",\n    \"primerApellido\": \"Hernandez\",\n    \"segundoApellido\": \"Fonseca\",\n    \"telefono\": \"3163390133\",\n    \"direccion\": \"Calle 5 #6-95\",\n    \"ciudadResidencia\": \"Bogotá\"\n  }\n}")
        })),
        @ApiResponse(code = 400, message = "Tipo de documento inválido", response = ErrorResponse.class, examples = @io.swagger.annotations.Example(value = {
            @io.swagger.annotations.ExampleProperty(mediaType = "application/json", value = "{\n  \"error\": 400,\n  \"message\": \"Tipo de documento inválido: A\"\n}")
        })),
        @ApiResponse(code = 404, message = "Cliente no encontrado", response = ErrorResponse.class, examples = @io.swagger.annotations.Example(value = {
            @io.swagger.annotations.ExampleProperty(mediaType = "application/json", value = "{\n  \"error\": 404,\n  \"message\": \"Cliente no encontrado: tipoDocumento=C numeroDocumento=23456789\"\n}")
        })),
        @ApiResponse(code = 500, message = "Error interno del servidor", response = ErrorResponse.class, examples = @io.swagger.annotations.Example(value = {
            @io.swagger.annotations.ExampleProperty(mediaType = "application/json", value = "{\n  \"error\": 500,\n  \"message\": \"Error interno al procesar la solicitud\"\n}")
        }))
    })
    @GetMapping("/{tipoDocumento}/{numeroDocumento}")
    public ResponseEntity<?> obtenerCliente(
            @ApiParam(value = "Tipo de documento del cliente (C: Cédula, P: Pasaporte)", required = true)
            @PathVariable String tipoDocumento,
            @ApiParam(value = "Número de documento del cliente", required = true)
            @PathVariable String numeroDocumento) {

        logger.info("Solicitud recibida para obtener cliente con tipoDocumento={} y numeroDocumento={}", tipoDocumento, numeroDocumento);

        // Validar tipo de documento
        if (!"C".equals(tipoDocumento) && !"P".equals(tipoDocumento)) {
            String mensaje = "Tipo de documento inválido: " + tipoDocumento;
            logger.error(mensaje);
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), mensaje));
        }

        try {
            Cliente cliente = clienteService.obtenerCliente(tipoDocumento, numeroDocumento);

            if (cliente == null) {
                String mensaje = "Cliente no encontrado: tipoDocumento=" + tipoDocumento + " numeroDocumento=" + numeroDocumento;
                logger.warn(mensaje);
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(new ErrorResponse(HttpStatus.NOT_FOUND.value(), mensaje));
            }

            logger.debug("Devolviendo información del cliente: {}", cliente.getPrimerNombre());
            SuccessResponse<Cliente> successResponse = new SuccessResponse<>(HttpStatus.OK.value(), cliente);
            return ResponseEntity.ok(successResponse);
        } catch (Exception ex) {
            String mensaje = "Error interno al procesar la solicitud";
            logger.error(mensaje, ex);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), mensaje));
        }
    }
}
