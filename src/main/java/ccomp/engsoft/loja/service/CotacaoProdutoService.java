package ccomp.engsoft.loja.service;

import ccomp.engsoft.loja.dao.CotacaoProdutoDao;
import ccomp.engsoft.loja.dao.GenericDao;
import ccomp.engsoft.loja.model.estoque.CotacaoProduto;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.persistence.TypedQuery;
import java.util.List;

@Service
public class CotacaoProdutoService extends GenericCrudService<CotacaoProduto, Integer>{

    final CotacaoProdutoDao dao;

    public CotacaoProdutoService(CotacaoProdutoDao dao) {
        this.dao = dao;
    }

    public List<CotacaoProduto> getAll(String nomeProduto, String codigo){
        return dao.getAll(nomeProduto, codigo);
    }

    public List<String> getNomesProdutosLike(String nomeProduto){
       return this.dao.getNomesProdutosLike(nomeProduto);
    }

    public List<String> getByNomesFornecedoresLike(String nomeFornecedor){
        return this.dao.getNomesFornecedoresLike(nomeFornecedor);
    }

    public List<String> getTelefonesByNomeFornecedor(String nomeFornecedor){
        return this.dao.getTelefonesByNomeFornecedor(nomeFornecedor);
    }

    @Override
    protected GenericDao<CotacaoProduto, Integer> getDao() {
        return dao;
    }
}
