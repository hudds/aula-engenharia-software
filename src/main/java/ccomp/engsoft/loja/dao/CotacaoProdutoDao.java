package ccomp.engsoft.loja.dao;

import ccomp.engsoft.loja.model.estoque.CotacaoProduto;
import ccomp.engsoft.loja.util.DaoUtil;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CotacaoProdutoDao extends GenericDao<CotacaoProduto, Integer>{

    protected CotacaoProdutoDao(EntityManager em) {
        super(em);
    }

    @Override
    protected Class<CotacaoProduto> getEntityClass() {
        return CotacaoProduto.class;
    }


    public List<String> getNomesProdutosLike(String nomeProduto){
        String jpql = "select distinct c.nomeProduto from CotacaoProduto c where c.nomeProduto like :pNome";
        TypedQuery<String> query = getEntityManager().createQuery(jpql, String.class);
        query.setParameter("pNome", "%"+nomeProduto+"%");
        return query.getResultList();
    }

    public List<String> getNomesFornecedoresLike(String nomeFornecedor){
        String jpql = "select distinct c.contatoFornecedor.nome from CotacaoProduto c where c.contatoFornecedor.nome like :pNome";
        TypedQuery<String> query = getEntityManager().createQuery(jpql, String.class);
        query.setParameter("pNome", "%"+nomeFornecedor+"%");
        return query.getResultList();
    }

    public List<String> getTelefonesByNomeFornecedor(String nomeFornecedor){
        String jpql = "select distinct c.contatoFornecedor.telefone from CotacaoProduto c where c.contatoFornecedor.nome like :pNome";
        TypedQuery<String> query = getEntityManager().createQuery(jpql, String.class);
        query.setParameter("pNome", "%"+nomeFornecedor+"%");
        return query.getResultList();
    }

    public List<CotacaoProduto> getAll(String nomeProduto, String codigoProduto){
        TypedQuery<CotacaoProduto> query = DaoUtil.criaQueryPorProduto(null, getEntityClass(), getEntityManager(), nomeProduto, codigoProduto);
        return query.getResultList();
    }
}
