package ccomp.engsoft.loja.validator;

import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@Component
@FacesValidator
public class ConfirmacaoSenhaValidator implements Validator<String> {

    @Override
    public void validate(FacesContext context, UIComponent component, String confirmacaoSenha) throws ValidatorException {
        System.out.println(" ConfirmacaoSenhaValidator called for " + confirmacaoSenha);

        System.out.println("UIComponent attributes: ");
        component.getAttributes().forEach((n, v) -> {
            System.out.println("\tAttribute: " + n);
            System.out.println("\tValue: " + v);
        });
        System.out.println();
        UIInput uiInput = (UIInput) (context.getViewRoot().findComponent("novaSenha"));
        String novaSenha = (String) uiInput.getValue();
        System.out.println("novaSenha value: " + novaSenha);
        if(confirmacaoSenha == null || novaSenha == null){
            return;
        }

        if(!novaSenha.equals(confirmacaoSenha)){
            throw new ValidatorException(new FacesMessage("A senha da confirmação é diferente da nova senha inserida."));
        }

    }
}
