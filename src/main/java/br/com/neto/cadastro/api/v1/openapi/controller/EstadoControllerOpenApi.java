package br.com.neto.cadastro.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import br.com.neto.cadastro.api.v1.model.EstadoModel;
import br.com.neto.cadastro.api.v1.model.input.EstadoInput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "estados")
public interface EstadoControllerOpenApi {

    @ApiOperation("Lista os estados")
    public CollectionModel<EstadoModel> listar();
    
    @ApiOperation("Busca um Estado por ID")
    @ApiResponses({
        @ApiResponse(code = 400, message = "ID do Estado inválido"),
        @ApiResponse(code = 404, message = "Estado não encontrada")
    })
    public EstadoModel buscar(
            @ApiParam(value = "ID de um Estado", example = "1")
            Long estadoId);
    
    @ApiOperation("Cadastra um Estado")
    @ApiResponses({
        @ApiResponse(code = 201, message = "Estado cadastrada"),
    })
    public EstadoModel adicionar(
            @ApiParam(name = "corpo", value = "Representação de um novo Estado")
            EstadoInput estadoInput);
    
    @ApiOperation("Atualiza uma cidade por ID")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Cidade atualizada"),
        @ApiResponse(code = 404, message = "Cidade não encontrada")
    })
    public EstadoModel atualizar(
            @ApiParam(value = "ID de uma cidade", example = "1") 
            Long estadoId,

            @ApiParam(name = "corpo", value = "Representação de um estado com os novos dados")
            EstadoInput estadoInput);

    @ApiOperation("Exclui um estado por ID")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Estado excluído"),
        @ApiResponse(code = 404, message = "Estado não encontrado")
    })
    public void remover(
            @ApiParam(value = "ID de um Estado", example = "1")
            Long estadoId);


}
