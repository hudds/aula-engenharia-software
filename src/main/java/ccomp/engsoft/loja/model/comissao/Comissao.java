package ccomp.engsoft.loja.model.comissao;

import ccomp.engsoft.loja.model.seguranca.Usuario;
import ccomp.engsoft.loja.model.venda.Venda;
import ccomp.engsoft.loja.util.DateUtil;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Null;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;

@Entity
public class Comissao {
    @Id
    private int id;
    private LocalDate primeiraDataPeriodo;
    private LocalDate segundaDataPeriodo;
    private BigDecimal valorTotalVendido;
    private BigDecimal taxaComissao;
    @ManyToOne
    private Usuario usuario;

    public Comissao(){ }
    public Comissao(Collection<Venda> vendas, Usuario usuario, BigDecimal taxaComissao){
        if(usuario.getId() == null){
            throw new NullPointerException("id do Usuario eh null");
        }
        this.usuario = usuario;
        this.taxaComissao = taxaComissao;
        obtemDados(vendas);
    }

    private void obtemDados(Collection<Venda> vendas){
        LocalDate menorData = null;
        LocalDate maiorData = null;
        valorTotalVendido = BigDecimal.ZERO;
        for(Venda venda :  vendas){
            validaUsuarioVenda(venda);
            valorTotalVendido = valorTotalVendido.add(venda.getValorTotal());
            menorData = DateUtil.menorDasDatas(menorData, venda.getData().toLocalDate());
            maiorData = DateUtil.maiorDasDatas(maiorData, venda.getData().toLocalDate());
        }
        primeiraDataPeriodo = menorData;
        segundaDataPeriodo = maiorData;
    }

    private void validaUsuarioVenda(Venda venda) {
        if(!venda.getUsuario().equals(this.usuario)){
            throw new IllegalArgumentException("A venda com o id " + venda + " nao pertence ao usuario com o id " + usuario.getId());
        }
    }

    public BigDecimal getValorComissao(){
        if(this.valorTotalVendido != null && this.taxaComissao != null){
            return this.valorTotalVendido.multiply(taxaComissao);
        }
        return null;
    }

}
