package ccomp.engsoft.loja.util;

import ccomp.engsoft.loja.model.estoque.Produto;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.lang.reflect.Field;

public class DaoUtil {

    public static <E> TypedQuery<E> criaQueryPorProduto(String colunaProduto, Class<E> entityClass, EntityManager em, String nomeProduto, String codigoProduto){
        String jpql = "select e from " + entityClass.getSimpleName() + " e";

        String and = nomeProduto != null && codigoProduto != null ? "and " : "";
        colunaProduto = colunaProduto == null ? Produto.class.getSimpleName() : colunaProduto;

        Field campoProduto;
        try {
            campoProduto = entityClass.getField(colunaProduto);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }

        if(!campoProduto.getType().isAssignableFrom(Produto.class)){
            throw new IllegalArgumentException("campo " + campoProduto + " nao eh do tipo " + Produto.class.getSimpleName());
        };

        if(nomeProduto != null || codigoProduto != null){
            jpql += "where ";
            if(nomeProduto != null){
                jpql += "e."+colunaProduto+".nome like :pNome ";
            }
            if(codigoProduto != null){
                jpql += and + "e."+colunaProduto+".codigo like :pCodigo ";
            }
        }

        TypedQuery<E> query = em.createQuery(jpql, entityClass);
        if(nomeProduto != null){
            query.setParameter("pNome", "%"+nomeProduto+"%");
        }

        if(codigoProduto != null){
            query.setParameter("pCodigo", "%"+codigoProduto+"%");
        }
        return query;
    }

}
