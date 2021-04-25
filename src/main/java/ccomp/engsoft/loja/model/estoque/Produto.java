package ccomp.engsoft.loja.model.estoque;

import ccomp.engsoft.loja.model.Identifiable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Produto implements Identifiable<String>, Serializable {
    @Id
    @NotBlank
    private String codigo;
    @NotBlank
    private String nome;
    @NotNull
    @DecimalMin(value = "0.0")
    private BigDecimal valorBase;
    @NotNull
    @Min(0)
    private Integer quantidade;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getValorBase() {
        return valorBase;
    }

    public void setValorBase(BigDecimal valorBase) {
        this.valorBase = valorBase;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produto)) return false;
        Produto produto = (Produto) o;
        if(getId() != null){
            return Objects.equals(getId(), produto.getId());
        }
        return Objects.equals(codigo, produto.codigo) && Objects.equals(nome, produto.nome) && Objects.equals(valorBase, produto.valorBase) && Objects.equals(quantidade, produto.quantidade);
    }

    @Override
    public int hashCode() {
        if(getId() != null){
            return Objects.hash(getId());
        }
        return Objects.hash(codigo, nome, valorBase, quantidade);
    }

    @Override
    public String getId() {
        return getCodigo();
    }

    @Override
    public String toString() {
        return "Produto{" +
                "codigo='" + codigo + '\'' +
                ", nome='" + nome + '\'' +
                ", valorBase=" + valorBase +
                ", quantidade=" + quantidade +
                '}';
    }
}
