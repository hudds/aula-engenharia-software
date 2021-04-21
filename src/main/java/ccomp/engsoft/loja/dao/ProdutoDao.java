package ccomp.engsoft.loja.dao;

import ccomp.engsoft.loja.model.estoque.Produto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class ProdutoDao extends GenericDao<Produto, String>{

    protected ProdutoDao(EntityManager em) {
        super(em);
    }

    @Override
    protected Class<Produto> getEntityClass() {
        return Produto.class;
    }
}
