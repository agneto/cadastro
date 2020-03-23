package br.com.neto.cadastro.api.v1.model;

import java.time.OffsetDateTime;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;

@Relation(collectionRelation = "estados")
public class EstadoModel extends RepresentationModel<EstadoModel> {

    @ApiModelProperty(value = "ID da cidade", example = "1")
    private Long id;

    @ApiModelProperty(example = "Santa Catarina")
    private String nome;

    @ApiModelProperty(example = "SC")
    private String sigla;

    @ApiModelProperty(example = "2020-03-23T20:34:04Z")
    private OffsetDateTime dataCriacao;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return this.sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public OffsetDateTime getDataCriacao() {
        return this.dataCriacao;
    }

    public void setDataCriacao(OffsetDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

}
