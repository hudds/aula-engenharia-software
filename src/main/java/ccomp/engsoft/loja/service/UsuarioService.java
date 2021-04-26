package ccomp.engsoft.loja.service;

import ccomp.engsoft.loja.dao.GenericDao;
import ccomp.engsoft.loja.dao.UsuarioDao;
import ccomp.engsoft.loja.model.seguranca.Usuario;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

@Service
public class UsuarioService extends GenericCrudService<Usuario, Integer> implements UserDetailsService {
    private final UsuarioDao dao;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioDao dao, PasswordEncoder passwordEncoder) {
        this.dao = dao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected GenericDao<Usuario, Integer> getDao() {
        return dao;
    }

    public Usuario getByNomeDeUsuario(String nomeDeUsuario){
        return dao.getByNomeDeUsuario(nomeDeUsuario);
    }

    @Override
    @Transactional
    public void insert(Usuario usuario) {
        hashSenha(usuario);
        super.insert(usuario);
    }

    @Override
    @Transactional
    public void update(Usuario usuario) {
        hashSenha(usuario);
        super.update(usuario);
    }

    public boolean senhaPertenceAoUsuario(String nomeDeUsuario, String senha){

        Usuario usuario = getByNomeDeUsuario(nomeDeUsuario);
        if(usuario == null){
            throw new UsernameNotFoundException(nomeDeUsuario);
        }

        return passwordEncoder.matches(senha, usuario.getSenha());
    }

    private void hashSenha(Usuario usuario){
        usuario.setSenha(hashSenha(usuario.getSenha()));
    }

    private String hashSenha(String senha){
        return passwordEncoder.encode(senha);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            Usuario usuario = getByNomeDeUsuario(username);
            if(usuario == null){
                throw new UsernameNotFoundException(username);
            }
            return usuario;

    }
}
