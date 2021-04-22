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
import java.util.List;

@Repository
public class ProdutoDao extends GenericDao<Produto, String>{

    protected ProdutoDao(EntityManager em) {
        super(em);
    }

    @Override
    protected Class<Produto> getEntityClass() {
        return Produto.class;
    }

    public List<Produto> getAll(String nome, BigDecimal valor) {
        if(nome == null && valor == null){
            return getAll();
        }
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Produto> cr = cb.createQuery(Produto.class);
        Root<Produto> root = cr.from(Produto.class);

        List<Predicate> predicates = new ArrayList<>();

        if(nome != null){
            predicates.add(cb.like(root.get("nome"), "%" + nome+ "%"));
        }

        if(valor != null){
            predicates.add(cb.equal(root.get("valorBase"), valor));
        }

        cr.select(root).where(predicates.toArray(new Predicate[0]));
        TypedQuery<Produto> query = getEntityManager().createQuery(cr);
        return query.getResultList();
    }
}
