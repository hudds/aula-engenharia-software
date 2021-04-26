package ccomp.engsoft.loja.controller;

import ccomp.engsoft.loja.model.estoque.Contato;
import ccomp.engsoft.loja.model.estoque.PedidoAbastecimento;
import ccomp.engsoft.loja.model.estoque.Produto;
import ccomp.engsoft.loja.service.PedidoService;
import ccomp.engsoft.loja.service.ProdutoService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.time.LocalDateTime;
import java.util.List;

@Named("pedidoController")
@ViewScoped
@Component
public class PedidoController {

    private final PedidoService pedidoService;
    private final ProdutoService produtoService;
    private PedidoAbastecimento pedidoAbastecimento;
    private List<PedidoAbastecimento> pedidosCache;
    private List<PedidoAbastecimento> filteredPedidos;

    @Inject
    public PedidoController(PedidoService pedidoService, ProdutoService produtoService) {
        this.pedidoService = pedidoService;
        this.produtoService = produtoService;
        limpar();
    }

    private void limpar(){
        pedidoAbastecimento = new PedidoAbastecimento();
        pedidoAbastecimento.setContatoCliente(new Contato());
        pedidosCache = null;
    }

    public List<PedidoAbastecimento> getPedidos() {
        if(pedidosCache == null){
            pedidosCache = pedidoService.getAll();
        }
        return pedidosCache;
    }

    public PedidoAbastecimento getPedido() {
        return pedidoAbastecimento;
    }

    public void fazerPedido(){
        pedidoAbastecimento.setData(LocalDateTime.now());
        pedidoService.insert(pedidoAbastecimento);
        limpar();
    }

    public void deletarPedido(Integer id){
        pedidoService.delete(id);
        limpar();
    }

    public List<Produto> getProdutos() {
        return this.produtoService.getAll();
    }

    public List<PedidoAbastecimento> getFilteredPedidos() {
        return filteredPedidos;
    }

    public void setFilteredPedidos(List<PedidoAbastecimento> filteredPedidos) {
        this.filteredPedidos = filteredPedidos;
    }
}
