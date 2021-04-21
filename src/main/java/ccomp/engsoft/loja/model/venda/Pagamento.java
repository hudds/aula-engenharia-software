package ccomp.engsoft.loja.model.venda;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
public class Pagamento {
    @Id
    private Integer id;
    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal valor;
    @NotNull
    @Enumerated(EnumType.STRING)
    private FormaPagamento forma;
    @NotNull
    @Enumerated(EnumType.STRING)
    private StatusPagamento status;

    public Pagamento(BigDecimal valor){
        this.valor = valor;
    }

    public Pagamento(){

    }

    public Integer getId() {
        return id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public FormaPagamento getForma() {
        return forma;
    }

    public void setForma(FormaPagamento forma) {
        this.forma = forma;
    }

    public StatusPagamento getStatus() {
        return status;
    }

    public void setStatus(StatusPagamento status) {
        this.status = status;
    }


}
