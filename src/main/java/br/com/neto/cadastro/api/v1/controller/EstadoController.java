package br.com.neto.cadastro.api.v1.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.neto.cadastro.api.v1.assembler.EstadoInputDisassembler;
import br.com.neto.cadastro.api.v1.assembler.EstadoModelAssembler;
import br.com.neto.cadastro.api.v1.model.EstadoModel;
import br.com.neto.cadastro.api.v1.model.input.EstadoInput;
import br.com.neto.cadastro.api.v1.openapi.controller.EstadoControllerOpenApi;
import br.com.neto.cadastro.domain.model.Estado;
import br.com.neto.cadastro.domain.repository.EstadoRepository;
import br.com.neto.cadastro.domain.service.CadastroEstadoService;

@RestController
@RequestMapping(path = "/v1/estados", produces = MediaType.APPLICATION_JSON_VALUE)
public class EstadoController implements EstadoControllerOpenApi {

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private CadastroEstadoService cadastroEstadoService;

    @Autowired
    private EstadoModelAssembler estadoModelAssembler;

    @Autowired
    private EstadoInputDisassembler estadoInputDisassembler;

    @Override
    @GetMapping
    public CollectionModel<EstadoModel> listar() {
        List<Estado> estados = this.estadoRepository.findAll();

        return this.estadoModelAssembler.toCollectionModel(estados);
    }

    @Override
    @GetMapping("/{estadoId}")
    public EstadoModel buscar(@PathVariable Long estadoId) {
        Estado estado = this.cadastroEstadoService.buscar(estadoId);
        return this.estadoModelAssembler.toModel(estado);
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EstadoModel adicionar(@RequestBody @Valid EstadoInput estadoInput) {
        Estado estado = this.estadoInputDisassembler.toDomainObject(estadoInput);

        estado = this.cadastroEstadoService.salvar(estado);

        EstadoModel estadoModel = this.estadoModelAssembler.toModel(estado);

        return estadoModel;
    }

    @Override
    @PutMapping("/{estadoId}")
    public EstadoModel atualizar(@PathVariable Long estadoId, @RequestBody @Valid EstadoInput estadoInput) {
    	try {
            Estado estadoAtual = this.cadastroEstadoService.buscar(estadoId);

            this.estadoInputDisassembler.copyToDomainObject(estadoInput, estadoAtual);

            estadoAtual = this.cadastroEstadoService.salvar(estadoAtual);

            return this.estadoModelAssembler.toModel(estadoAtual);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    @DeleteMapping("/{estadoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long estadoId) {
        this.cadastroEstadoService.excluir(estadoId);    
    }

}
