package ccomp.engsoft.loja.dao;

import ccomp.engsoft.loja.model.Identifiable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import java.io.Serializable;
import java.util.List;

@Repository
public abstract class GenericDao<E extends Identifiable<I>, I> implements Serializable {

    final EntityManager em;


    protected GenericDao(EntityManager em) {
        this.em = em;
    }

    protected abstract Class<E> getEntityClass();

    public E get(I id){
        return em.find(getEntityClass(), id);
    }

    public List<E> getAll(){
        String jpql = "select e from " + getEntityClass().getSimpleName() + " e";
        return em.createQuery(jpql, getEntityClass()).getResultList();
    }


    public void insert(E entity){
        I entityId = entity.getId();
        if(entityId != null && get(entityId) != null){
            throw new EntityExistsException("entity with id " + entityId + " already exists");
        }
        em.persist(entity);
    }

    public void update(E entity){
        if(get(entity.getId()) == null){
            throw new EntityNotFoundException("no entity with id " + entity.getId());
        }
        em.merge(entity);
    }

    public void delete(I id){
        E entity = get(id);
        if(entity == null){
            throw new EntityNotFoundException("no entity with id " + entity.getId());
        }
        em.remove(entity);
    }

    protected EntityManager getEntityManager(){
        return this.getEntityManager();
    }

}
