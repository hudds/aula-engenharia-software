package ccomp.engsoft.loja.model.venda;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class InfoNFe {
    @Id
    private String uuid;
    private String status;
    private String motivo;
    private String serie;
    private String modelo;
    private String recibo;
    private String chave;
    private String urlXml;
    private String urlDanfe;
    private String logSefaz;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getRecibo() {
        return recibo;
    }

    public void setRecibo(String recibo) {
        this.recibo = recibo;
    }

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public String getUrlXml() {
        return urlXml;
    }

    public void setUrlXml(String urlXml) {
        this.urlXml = urlXml;
    }

    public String getUrlDanfe() {
        return urlDanfe;
    }

    public void setUrlDanfe(String urlDanfe) {
        this.urlDanfe = urlDanfe;
    }

    public String getLogSefaz() {
        return logSefaz;
    }

    public void setLogSefaz(String logSefaz) {
        this.logSefaz = logSefaz;
    }
}
