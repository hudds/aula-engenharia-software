package ccomp.engsoft.loja.model.venda;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Carrinho {

    private BigDecimal valorTotal;
    private final Map<String, ItemCarrinho> itens = new HashMap<>();

    public void adicionaItemVenda(ItemCarrinho item){
        itens.put(item.getProduto().getCodigo(), item);
        calculaValorTotal();
    }

    public void removeItemVenda(String codigo){
        itens.remove(codigo);
        calculaValorTotal();
    }

    public void alteraQuantidade(String codigo, int quantidade){
        if(quantidade <= 0){
            removeItemVenda(codigo);
            return;
        }
        itens.put(codigo, itens.get(codigo).novaQuantidade(quantidade));
        calculaValorTotal();
    }

    public void adicionaQuantidade(String codigo, int quantidade){
        ItemCarrinho itemCarrinho = itens.get(codigo);
        alteraQuantidade(codigo, itemCarrinho.getQuantidade() + quantidade);
    }

    public List<ItemCarrinho> getItens(){
        return new ArrayList<>(itens.values());
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void limparCarrinho(){
        this.itens.clear();
    }

    private void calculaValorTotal(){
        valorTotal = BigDecimal.ZERO;
        itens.values().forEach(i -> valorTotal = valorTotal.add(i.getValorTotal()));
    }

}
