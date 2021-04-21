package ccomp.engsoft.loja.model.venda;

import ccomp.engsoft.loja.model.estoque.FinalProdutoWrapper;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * ItemVenda não é imutavel, o que pode ocasionar problemas, por isso ItemCarrinho foi criado
 */
public class ItemCarrinho {

    private final FinalProdutoWrapper produto;
    private final Integer quantidade;
    private final BigDecimal valorTotal;

    public ItemCarrinho(FinalProdutoWrapper produto, int quantidade) {
        validaProduto(produto);
        this.produto = produto;
        this.quantidade = quantidade;
        valorTotal = calculaValorTotal(produto, quantidade);
    }

    private void validaProduto(FinalProdutoWrapper produto) {
        if(produto == null){
            throw new NullPointerException("produto nao pode ser null");
        }
        if(produto.getCodigo() == null){
            throw new NullPointerException("codigo de produto nao pode ser null");
        }
    }

    public FinalProdutoWrapper getProduto() {
        return produto;
    }
    
    public Integer getQuantidade() {
        return quantidade;
    }

    public ItemCarrinho novaQuantidade(int quantidade){
        return new ItemCarrinho(this.produto, quantidade);
    }

    public BigDecimal getValorTotal(){
        return this.valorTotal;
    }

    private BigDecimal calculaValorTotal(FinalProdutoWrapper produto, int quantidade){
        return new BigDecimal(quantidade).multiply(produto.getValorBase());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemCarrinho that = (ItemCarrinho) o;
        return produto.equals(that.produto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(produto);
    }
}
