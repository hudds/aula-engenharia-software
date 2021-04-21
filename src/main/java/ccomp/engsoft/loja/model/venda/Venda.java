package ccomp.engsoft.loja.model.venda;

import br.com.caelum.stella.bean.validation.CPF;
import ccomp.engsoft.loja.model.seguranca.Usuario;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Venda {
    @Id
    private Integer id;
    @NotNull
    private LocalDateTime data;
    @NotBlank
    @CPF
    private String cpfCliente;
    @NotNull
    @DecimalMin("0.0")
    private BigDecimal valorTotal;
    @OneToMany
    @NotEmpty
    @Cascade(CascadeType.ALL)
    private Set<ItemVenda> itens;
    @ManyToOne
    private Usuario usuario;
    @OneToOne
    private Pagamento pagamento;
    @OneToOne
    private InfoNFe nfe;


    public void calculaValorTotal(){
        if(this.itens != null){
            valorTotal = BigDecimal.ZERO;
            itens.forEach(i -> valorTotal = valorTotal.add(i.getValorTotal()));
        }
    }

    public void insereItens(Collection<ItemVenda> itens){
        inicializaItens();
        this.itens.addAll(itens);
        calculaValorTotal();
    }

    public void insereItem(ItemVenda item){
        inicializaItens();
        this.itens.add(item);
        calculaValorTotal();
    }

    public ItemVenda getItem(String codigo){
        if(this.itens != null){
            return buscaItemVenda(codigo);

        }
        return null;
    }

    public boolean removeItem(ItemVenda item){
        return this.itens.remove(item);
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public Set<ItemVenda> getItens() {
        return new HashSet<>(itens);
    }

    private ItemVenda buscaItemVenda(String codigo) {
        for(ItemVenda item:  itens){
            if(item.getProduto().getCodigo().equals(codigo)){
                return item;
            }
        }
        return null;
    }

    private void inicializaItens() {
        if(this.itens == null){
            this.itens = new HashSet<>();
        }
    }


    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public void setItens(Set<ItemVenda> itens) {
        this.itens = itens;
    }

    public Integer getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public InfoNFe getNfe() {
        return nfe;
    }

    public void setNfe(InfoNFe nfe) {
        this.nfe = nfe;
    }
}
