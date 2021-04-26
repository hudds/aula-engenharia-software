package ccomp.engsoft.loja.controller;


import ccomp.engsoft.loja.controller.dto.AlteracaoSenhaDTO;
import ccomp.engsoft.loja.model.seguranca.Usuario;
import ccomp.engsoft.loja.service.UsuarioService;
import ccomp.engsoft.loja.util.Messages;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Component
@Named("usuarioController")
@ViewScoped
public class UsuarioController {

    private final UsuarioService usuarioService;
    private AlteracaoSenhaDTO alteracaoSenhaDTO;

    private final PasswordEncoder passwordEncoder;

    @Inject
    public UsuarioController(UsuarioService usuarioService, PasswordEncoder passwordEncoder) {
        this.usuarioService = usuarioService;
        this.passwordEncoder = passwordEncoder;
        limpar();
    }

    private void limpar(){
        this.alteracaoSenhaDTO = new AlteracaoSenhaDTO();
    }

    public void editaSenha(){
        Usuario usuario = getUsuarioLogado();
        usuario.setSenha(alteracaoSenhaDTO.getSenha());
        usuarioService.update(usuario);
        Messages.addInfo(null,"Senha alterada com sucesso", null);
        limpar();
    }

    public void checkSenhaAtual(FacesContext context, UIComponent component, Object value) throws ValidatorException{
        String senhaAtual = value.toString();
        System.out.println("Senha atual inserida: " + senhaAtual);
        if(!usuarioService.senhaPertenceAoUsuario(getNomeDeUsuarioLogado(), senhaAtual)){
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "A senha atual inserida está errada"
                    , null);
            throw new ValidatorException(msg);
        }
    }

    public void checkSenhaConfirmacao(FacesContext context, UIComponent component, Object value) throws ValidatorException{
        if(alteracaoSenhaDTO.getSenha() == null ||  value == null){
            return;
        }
        String senhaAtual = value.toString();
        if(!alteracaoSenhaDTO.getSenha().equals(senhaAtual)){
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "A confirmação de senha está diferente da senha inserida."
                    , null);
            throw new ValidatorException(msg);
        }
    }

    private Usuario getUsuarioLogado(){
        return usuarioService.getByNomeDeUsuario(getNomeDeUsuarioLogado());
    }

    private String getNomeDeUsuarioLogado(){
        return getAuthentication().getName();
    }

    private Authentication getAuthentication(){
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public AlteracaoSenhaDTO getSenha() {
        return alteracaoSenhaDTO;
    }

    public void setSenha(AlteracaoSenhaDTO alteracaoSenhaDTO) {
        this.alteracaoSenhaDTO = alteracaoSenhaDTO;
    }
}
