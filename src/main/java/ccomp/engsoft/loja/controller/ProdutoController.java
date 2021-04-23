package ccomp.engsoft.loja.controller;

import ccomp.engsoft.loja.model.estoque.Produto;
import ccomp.engsoft.loja.service.ProdutoService;
import ccomp.engsoft.loja.util.Messages;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityExistsException;
import java.io.Serializable;
import java.util.List;

@Component
@ViewScoped
@Named("produtoController")
public class ProdutoController implements Serializable {

    private final ProdutoService produtoService;
    private List<Produto> produtosCache = null;
    private Produto produto = new Produto();
    private boolean modoEdicao = false;

    @Inject
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }


    private void limpar(){
        produto = new Produto();
        this.produtosCache = null;
        this.modoEdicao = false;
    }

    public void registraProduto(){
        try {
            if (modoEdicao) {
                produtoService.update(produto);
            } else {
                produtoService.insert(produto);
            }
        } catch (DataIntegrityViolationException e){
            if(e.getCause() instanceof EntityExistsException) {
                Messages.addError(null, "Ja existe um produto com esse código.", null);
            } else {
                throw e;
            }
        }
        limpar();
    }

    public void deletaProduto(String codigo){
        System.out.println("deletando " + codigo);
        this.produtoService.delete(codigo);
        limpar();

    }

    public List<Produto> getProdutos() {
        if(produtosCache == null){
            produtosCache = produtoService.getAll();
        };
        return produtosCache;
    }

    public Produto getProduto() {
        return produto;
    }

    public void carregaProduto(String codigo){
        this.produto = produtoService.get(codigo);
        modoEdicao = true;
    }
}
