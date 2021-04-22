package ccomp.engsoft.loja.service;

import ccomp.engsoft.loja.dao.GenericDao;
import ccomp.engsoft.loja.model.Identifiable;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Component

public abstract class GenericCrudService<E extends Identifiable<I>, I> implements Serializable {

    public E get(I id){
        return getDao().get(id);
    }

    public List<E> getAll(){
        return getDao().getAll();
    }

    @Transactional
    public void insert(E entity){
        getDao().insert(entity);
    }

    @Transactional
    public void update(E entity){
       getDao().update(entity);
    }

    @Transactional
    public void delete(I id){
        getDao().delete(id);
    }

    protected abstract GenericDao<E, I> getDao();
}
