package ccomp.engsoft.loja.service;

import ccomp.engsoft.loja.dao.GenericDao;
import ccomp.engsoft.loja.dao.UsuarioDao;
import ccomp.engsoft.loja.model.seguranca.Usuario;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.persistence.NoResultException;

@Component
public class UsuarioService extends GenericCrudService<Usuario, Integer> implements UserDetailsService {
    final UsuarioDao dao;

    public UsuarioService(UsuarioDao dao) {
        this.dao = dao;
    }

    @Override
    protected GenericDao<Usuario, Integer> getDao() {
        return dao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            System.out.println("fetching " + username);
            return dao.getByNomeDeUsuario(username);
        } catch (Exception e){
            System.out.println("username " + username + " not found");
            if(e.getCause() instanceof NoResultException) {
                throw new UsernameNotFoundException(username);
            }
            throw e;
        }

    }
}
