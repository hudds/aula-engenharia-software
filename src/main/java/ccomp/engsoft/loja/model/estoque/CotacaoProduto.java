package ccomp.engsoft.loja.model.estoque;

import ccomp.engsoft.loja.model.Identifiable;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import org.hibernate.annotations.CascadeType;

@Entity
public class CotacaoProduto implements Identifiable<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    private String nomeProduto;
    @DecimalMin(value = "0.0")
    @NotNull
    private BigDecimal valor;
    @ManyToOne
    @NotNull
    @Cascade(CascadeType.ALL)
    private Contato contatoFornecedor;
    @ManyToOne
    @JoinColumn(nullable = true)
    private Produto produto;
    @NotNull
    private LocalDateTime dataAtualizacao;

    @Override
    public Integer getId() {
        return this.id;
    }

    public String getNomeProduto() {
        if(produto != null){
            this.nomeProduto = produto.getNome();
        }
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }


    public Contato getContatoFornecedor() {
        return contatoFornecedor;
    }

    public void setContatoFornecedor(Contato contatoFornecedor) {
        this.contatoFornecedor = contatoFornecedor;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {

        if(produto != null) {
            System.out.println("Alterando produto de uma cotacao. Nome produto: " + produto.getNome());
            this.nomeProduto = produto.getNome();
        }
        this.produto = produto;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CotacaoProduto)) return false;
        CotacaoProduto that = (CotacaoProduto) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "CotacaoProduto{" +
                "id=" + id +
                ", nomeProduto='" + nomeProduto + '\'' +
                ", valor=" + valor +
                ", contatoFornecedor=" + contatoFornecedor +
                ", produto=" + produto +
                ", dataAtualizacao=" + dataAtualizacao +
                '}';
    }
}
