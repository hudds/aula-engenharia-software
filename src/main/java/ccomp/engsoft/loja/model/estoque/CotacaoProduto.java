package ccomp.engsoft.loja.model.estoque;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class CotacaoProduto {
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
    private Contato contatoFornecedor;
    @ManyToOne
    @NotNull
    private Produto produto;
    @NotNull
    private LocalDateTime dataAtualizacao;

    public void getId(Integer id) {
        this.id = id;
    }

    public String getNomeProduto() {
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
        this.produto = produto;
    }
}
