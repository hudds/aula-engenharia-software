package ccomp.engsoft.loja.model.venda;

import ccomp.engsoft.loja.model.estoque.Produto;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class ItemVenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @DecimalMin(value = "0.0")
    private BigDecimal valorUnitario;
    private BigDecimal valorTotal;
    @NotNull
    private Integer quantidade = 1;
    @ManyToOne
    @NotNull
    private Produto produto;

    public ItemVenda(Produto produto) {
        setProduto(produto);
    }

    public ItemVenda(Produto produto, int quantidade) {
        setProduto(produto);
        setQuantidade(quantidade);
    }

    public ItemVenda() {

    }

    public Integer getId() {
        return id;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    private void calculaValorTotal(){
        if(this.valorTotal != null && this.quantidade != null){
            this.valorTotal = this.valorUnitario.multiply(new BigDecimal(quantidade));
        }
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        if(quantidade < 0){
            throw new IllegalArgumentException("quantidade nao pode ser menor do que 0");
        }
        this.quantidade = quantidade;
        calculaValorTotal();
    }

    public Produto getProduto() {
        return produto;
    }

    private void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
        calculaValorTotal();
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
        setValorUnitario(produto.getValorBase());

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemVenda itemVenda = (ItemVenda) o;
        return Objects.equals(produto, itemVenda.produto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(produto);
    }
}
