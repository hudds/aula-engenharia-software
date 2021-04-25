package ccomp.engsoft.loja.model.seguranca;

import org.springframework.security.core.GrantedAuthority;

public class Role implements GrantedAuthority {

    final String nivelAcesso;

    public Role(String nivelAcesso) {
        this.nivelAcesso = nivelAcesso;
    }

    @Override
    public String getAuthority() {
        return nivelAcesso;
    }
}
