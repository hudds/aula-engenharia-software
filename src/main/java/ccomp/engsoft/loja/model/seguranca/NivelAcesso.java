package ccomp.engsoft.loja.model.seguranca;

public enum NivelAcesso {
    ADMINISTRADOR("ROLE_ADMIN"), GERENTE("ROLE_GERENTE"), FUNCIONARIO("ROLE_FUNCIONARIO");

    private String role;

    NivelAcesso(String role){
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
