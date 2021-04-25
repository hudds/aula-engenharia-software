package ccomp.engsoft.loja.dao;

import ccomp.engsoft.loja.model.estoque.CotacaoProduto;
import ccomp.engsoft.loja.model.estoque.PedidoAbastecimento;
import ccomp.engsoft.loja.model.estoque.Produto;
import ccomp.engsoft.loja.util.DaoUtil;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PedidoDao extends GenericDao<PedidoAbastecimento, Integer>{

    protected PedidoDao(EntityManager em) {
        super(em);
    }

    @Override
    protected Class<PedidoAbastecimento> getEntityClass() {
        return PedidoAbastecimento.class;
    }

    public List<PedidoAbastecimento> getAll(String nomeProduto, String codigoProduto) {
        TypedQuery<PedidoAbastecimento> query = DaoUtil.criaQueryPorProduto(null, getEntityClass(), getEntityManager(), nomeProduto, codigoProduto);
        return query.getResultList();
    }
}
