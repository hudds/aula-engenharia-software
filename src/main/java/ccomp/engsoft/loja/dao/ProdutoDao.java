package ccomp.engsoft.loja.dao;

import ccomp.engsoft.loja.model.estoque.Produto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProdutoDao extends GenericDao<Produto, String>{

    protected ProdutoDao(EntityManager em) {
        super(em);
    }

    @Override
    protected Class<Produto> getEntityClass() {
        return Produto.class;
    }

    public List<Produto> getAll(String nome, BigDecimal valorBase) {
        Map<String, Object> attributes = new HashMap();
        attributes.put("nome", nome);
        attributes.put("valorBase", valorBase);
        return createQuery(attributes, true).getResultList();
    }
}
