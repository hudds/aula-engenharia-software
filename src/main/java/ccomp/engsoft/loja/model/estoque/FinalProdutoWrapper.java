package ccomp.engsoft.loja.model.estoque;

import java.util.Objects;

/**
 * O campo "codigo" da classe Produto não é imutavel devido ao fato de ser uma entidade JPA.
 * Esta classe foi feita com o intuito de protejer o atributo "codigo" de um objeto Produto.
 *
 **/
public class FinalProdutoWrapper extends Produto {
    
    final String codigo;

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
    public void setCodigo(String codigo){}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FinalProdutoWrapper)) return false;
        if (!super.equals(o)) return false;
        FinalProdutoWrapper that = (FinalProdutoWrapper) o;
        return Objects.equals(codigo, that.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), codigo);
    }
}
