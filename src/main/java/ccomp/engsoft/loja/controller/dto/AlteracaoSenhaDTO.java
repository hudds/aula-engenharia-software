package ccomp.engsoft.loja.controller.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AlteracaoSenhaDTO {


    @NotBlank
    private String senhaAtual;

    @NotBlank
    @Size(min = 8)
    private String senha;
    @NotBlank
    private String confirmacao;

    public boolean confirmacaoCorreta(){
        return this.senha.equals(confirmacao);
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        System.out.println(String.format("callled setSenha on %s with the value: %s", this.getClass().getSimpleName(), senha));
        this.senha = senha;
    }

    public String getConfirmacao() {
        return confirmacao;
    }

    public void setConfirmacao(String confirmacao) {
        this.confirmacao = confirmacao;
    }

    public String getSenhaAtual() {
        return senhaAtual;
    }

    public void setSenhaAtual(String senhaAtual) {
        this.senhaAtual = senhaAtual;
    }

}
