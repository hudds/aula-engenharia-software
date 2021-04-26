package ccomp.engsoft.loja.service;

import ccomp.engsoft.loja.dao.GenericDao;
import ccomp.engsoft.loja.dao.ProdutoDao;
import ccomp.engsoft.loja.model.estoque.Produto;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService extends GenericCrudService<Produto, String>{
    final ProdutoDao produtoDao;

    public ProdutoService(ProdutoDao produtoDao) {
        this.produtoDao = produtoDao;
    }

    @Override
    protected GenericDao<Produto, String> getDao() {
        return produtoDao;
    }
}
