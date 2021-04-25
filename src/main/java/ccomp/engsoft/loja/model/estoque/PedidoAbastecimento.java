package ccomp.engsoft.loja.model.estoque;

import ccomp.engsoft.loja.model.Identifiable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class PedidoAbastecimento implements Identifiable<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    private String nomeProduto;
    @ManyToOne
    @JoinColumn(nullable = true)
    private Produto produto;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = true)
    private Contato contatoCliente;
    private LocalDateTime data;
    private Integer quantidade;

    public Integer getId() {
        return id;
    }

    public String getNomeProduto() {
        if(produto != null){
            this.nomeProduto = produto.getNome();
        }
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        if(produto != null) {
            this.nomeProduto = produto.getNome();
        }
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PedidoAbastecimento)) return false;
        PedidoAbastecimento that = (PedidoAbastecimento) o;
        if (getId() != null) {
            return Objects.equals(getId(), that.getId());
        }
        return Objects.equals(id, that.id) && Objects.equals(nomeProduto, that.nomeProduto) && Objects.equals(produto, that.produto) && Objects.equals(quantidade, that.quantidade) && Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        if (getId() != null) {
            return Objects.hash(getId());
        }
        return Objects.hash(id, nomeProduto, produto, quantidade, data);
    }

    public Contato getContatoCliente() {
        return contatoCliente;
    }

    public void setContatoCliente(Contato contatoCliente) {
        this.contatoCliente = contatoCliente;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }
}
