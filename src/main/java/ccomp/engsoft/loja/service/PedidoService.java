package ccomp.engsoft.loja.service;

import ccomp.engsoft.loja.dao.GenericDao;
import ccomp.engsoft.loja.dao.PedidoDao;
import ccomp.engsoft.loja.model.estoque.PedidoAbastecimento;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PedidoService extends GenericCrudService<PedidoAbastecimento, Integer>{
    final PedidoDao pedidoDao;

    public PedidoService(PedidoDao pedidoDao) {
        this.pedidoDao = pedidoDao;
    }

    @Override
    protected GenericDao<PedidoAbastecimento, Integer> getDao() {
        return pedidoDao;
    }

    public List<PedidoAbastecimento> getAll(String nome, String codigoProduto) {
        return pedidoDao.getAll(nome, codigoProduto);
    }
}
