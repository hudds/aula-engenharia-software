package ccomp.engsoft.loja.converter;

import ccomp.engsoft.loja.model.estoque.Produto;
import ccomp.engsoft.loja.service.ProdutoService;
import org.springframework.stereotype.Component;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;

@Component("produtoConverter")
public class ProdutoConverter implements Converter<Produto> {

    private final ProdutoService produtoService;

    @Inject
    public ProdutoConverter(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @Override
    public Produto getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        if(s == null || s.trim().isEmpty()){
            return null;
        }
        Produto produto = produtoService.get(s);
        if(produto == null){
            produto = new Produto();
            produto.setCodigo(s);
        }
        return produto;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Produto o) {
        if(o == null){
            return "";
        }
        return o.getCodigo();
    }
}
