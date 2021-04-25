package ccomp.engsoft.loja.model.seguranca;

import java.util.Arrays;
import java.util.List;

public enum NivelAcesso {
    ADMINISTRADOR(new Role("ROLE_ADMIN"), new Role("ROLE_GERENTE"), new Role("ROLE_FUNCIONARIO")),
    GERENTE( new Role("ROLE_GERENTE"), new Role("ROLE_FUNCIONARIO")),
    FUNCIONARIO(new Role("ROLE_FUNCIONARIO"));

    private List<Role> roles;

    NivelAcesso(Role... roles){
        this.roles = Arrays.asList(roles);
    }

    public List<Role> getRoles() {
        return roles;
    }
}
