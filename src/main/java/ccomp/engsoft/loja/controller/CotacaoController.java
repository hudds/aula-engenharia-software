package ccomp.engsoft.loja.controller;

import ccomp.engsoft.loja.model.estoque.Contato;
import ccomp.engsoft.loja.model.estoque.CotacaoProduto;
import ccomp.engsoft.loja.model.estoque.Produto;
import ccomp.engsoft.loja.service.CotacaoProdutoService;
import ccomp.engsoft.loja.service.ProdutoService;
import ccomp.engsoft.loja.util.Messages;
import org.springframework.stereotype.Component;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@ViewScoped
@Named("cotacaoController")
public class CotacaoController implements Serializable {

    private final CotacaoProdutoService cotacaoProdutoService;
    private final ProdutoService produtoService;

    private List<CotacaoProduto> cotacaoCache;

    private boolean modoEdicao;
    private CotacaoProduto cotacaoProduto;

    @Inject
    public CotacaoController(CotacaoProdutoService cotacaoProdutoService, ProdutoService produtoService) {
        this.cotacaoProdutoService = cotacaoProdutoService;
        this.produtoService = produtoService;
        limpar();
    }

    public void insereCotacao() {
        cotacaoProduto.setDataAtualizacao(LocalDateTime.now());
        cotacaoProdutoService.insert(cotacaoProduto);
        Messages.addInfo(null, "Cotação salva com sucesso.", null);
        limpar();
    }

    public void editaCotacao() {
        cotacaoProdutoService.update(cotacaoProduto);
        Messages.addInfo(null, "Cotação salva com sucesso.", null);
        limpar();
    }

    private void limpar() {
        this.cotacaoProduto = new CotacaoProduto();
        this.cotacaoProduto.setContatoFornecedor(new Contato());
        this.cotacaoProduto.setProduto(new Produto());
        this.modoEdicao = false;
        this.cotacaoCache = null;
    }

    public List<String> getNomesProdutosLike(String query) {
        List<String> all = cotacaoProdutoService.getNomesProdutosLike(query);
        return all;
    }


    public List<String> getNomesFornecedoresLike(String nomeFornecedor){
        return this.cotacaoProdutoService.getByNomesFornecedoresLike(nomeFornecedor);
    }

    public List<String> getTelefonesByNomeFornecedor(){
        if(this.cotacaoProduto.getContatoFornecedor().getNome() != null) {
            return this.cotacaoProdutoService.getTelefonesByNomeFornecedor(this.cotacaoProduto.getContatoFornecedor().getNome());
        }
         return null;
    }

    public List<CotacaoProduto> getCotacoes() {
        if (this.cotacaoCache == null) {
            cotacaoCache = cotacaoProdutoService.getAll();
        }
        return cotacaoCache;
    }

    public void deletaCotacao(Integer cotacaoId) {
        cotacaoProdutoService.delete(cotacaoId);
        limpar();
    }

    public CotacaoProduto getCotacaoProduto() {
        return cotacaoProduto;
    }

    public void carregaCotacaoProduto(Integer id) {
        this.cotacaoProduto = cotacaoProdutoService.get(id);
        this.modoEdicao = true;
    }

    public boolean isModoEdicao() {
        return modoEdicao;
    }

    public List<Produto> getProdutos() {
        new CotacaoProduto().setProduto(null);
        List<Produto> all = this.produtoService.getAll();
        System.out.println(all);
        return all;
    }

}
