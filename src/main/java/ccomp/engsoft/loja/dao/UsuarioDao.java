package ccomp.engsoft.loja.dao;

import ccomp.engsoft.loja.model.estoque.PedidoAbastecimento;
import ccomp.engsoft.loja.model.seguranca.Usuario;
import ccomp.engsoft.loja.util.DaoUtil;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UsuarioDao extends GenericDao<Usuario, Integer>{

    protected UsuarioDao(EntityManager em) {
        super(em);
    }

    @Override
    protected Class<Usuario> getEntityClass() {
        return Usuario.class;
    }

    public Usuario getByNomeDeUsuario(String nomeDeUsuario){
        String jpql = String.format("select u from %s u where u.nomeDeUsuario = :pNomeDeUsuario", getEntityClass().getSimpleName());
        TypedQuery<Usuario> query = getEntityManager().createQuery(jpql, Usuario.class);
        query.setParameter("pNomeDeUsuario", nomeDeUsuario);
        return query.getSingleResult();
    }
}
