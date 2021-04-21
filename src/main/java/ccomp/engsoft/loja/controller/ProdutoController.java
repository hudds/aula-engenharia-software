package ccomp.engsoft.loja.controller;

import ccomp.engsoft.loja.dao.ProdutoDao;
import ccomp.engsoft.loja.model.estoque.Produto;
import ccomp.engsoft.loja.service.ProdutoService;
import ccomp.engsoft.loja.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityExistsException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
@Named("produtoController")
public class ProdutoController implements Serializable {

    @Autowired
    private ProdutoService produtoService;

    private Produto produto = new Produto();
    private boolean modoEdicao = false;


    private void limpar(){
        produto = new Produto();
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
        modoEdicao = false;
    }

    public List<Produto> getProdutos() {
        return produtoService.getAll();
    }

    public Produto getProduto() {
        return produto;
    }

    public void carregaProduto(String codigo){
        this.produto = produtoService.get(codigo);
        modoEdicao = true;
    }
}
