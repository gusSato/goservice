package com.soulcode.goserviceapp.domain;

import com.soulcode.goserviceapp.domain.enums.Perfil;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Min;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Prestador extends Usuario{

    private String descricao;

    @Min(value = 10, message = "O valor minimo de taxa cobrada por hora é 10.")
    private Float taxaPorHora;

    @ManyToMany
    @JoinTable(
            name = "prestadores_servicos",
            joinColumns = @JoinColumn(name = "prestador_id"),
            inverseJoinColumns = @JoinColumn(name = "servico_id")
    )
    private List<Servico> especialidade;

    public Prestador() {
        super();
        setPerfil(Perfil.PRESTADOR);
    }

    public Prestador(Long id, String nome, String email, String senha, Boolean habilitado, Perfil perfil) {
        super(id, nome, email, senha, habilitado, perfil);
    }

    public Prestador(Long id, String nome, String email, String senha, Boolean habilitado, Perfil perfil, String descricao, Float taxaPorHora, List<Servico> especialidade) {
        super(id, nome, email, senha, habilitado, perfil);
        this.descricao = descricao;
        this.taxaPorHora = taxaPorHora;
        this.especialidade = especialidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Float getTaxaPorHora() {
        return taxaPorHora;
    }

    public void setTaxaPorHora(Float taxaPorHora) {
        this.taxaPorHora = taxaPorHora;
    }

    public List<Servico> getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(List<Servico> especialidade) {
        this.especialidade = especialidade;
    }

    public void addEspecialidade(Servico servico){
        if (especialidade == null){
            especialidade = new ArrayList<>();
        }
        especialidade.add(servico);
    }

    public boolean removeEspecialidade(Servico servico){
        if (especialidade != null){
            return especialidade.remove(servico);
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prestador that = (Prestador) o;
        return Objects.equals(descricao, that.descricao) &&
                Objects.equals(taxaPorHora, that.taxaPorHora) &&
                Objects.equals(especialidade, that.especialidade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(descricao, taxaPorHora, especialidade);
    }
}
