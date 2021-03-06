package ccomp.engsoft.loja.model.estoque;

import java.util.Objects;

/**
 * O campo "codigo" da classe Produto não é imutavel devido ao fato de ser uma entidade JPA.
 * Esta classe foi feita com o intuito de protejer o atributo "codigo" de um objeto Produto.
 *
 **/
public class FinalProdutoWrapper extends Produto {
    
    private final String codigo;

    public FinalProdutoWrapper(Produto produto) {
        this.codigo = produto.getCodigo();
        setQuantidade(produto.getQuantidade());
        setNome(produto.getNome());
        setValorBase(produto.getValorBase());
    }

    @Override
    public String getCodigo() {
        return this.codigo;
    }

    @Override
    @Deprecated
    public void setCodigo(String codigo){
        throw new UnsupportedOperationException("nao e possivel alterar o codigo de um FinalProdutoWrapper");
    }
}
