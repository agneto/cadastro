package br.com.neto.cadastro.api.v1.model.input;

import javax.validation.constraints.NotBlank;

public class EstadoInput {
    
    @NotBlank
    private String nome;
    
    @NotBlank
    private String sigla;

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

}
