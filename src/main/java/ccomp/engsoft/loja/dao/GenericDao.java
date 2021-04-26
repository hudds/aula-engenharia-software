package ccomp.engsoft.loja.dao;

import ccomp.engsoft.loja.model.Identifiable;
import ccomp.engsoft.loja.model.estoque.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
        return this.em;
    }


    /**
     * Cria uma query dinamica, ignorando os atributos nulos ou em branco.
     * @param attributes Contem os nomes dos atributos e os valores a serem consultados.
     * @param stringAsLike se true, faz a query usando like para as strings, caso o contrário, irá buscar os dados onde a string é idêntica.
     * @return TypedQuery pronta para ser executada
     */
    protected TypedQuery<E> createQuery(Map<String, Object> attributes, boolean stringAsLike){
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<E> cr = cb.createQuery(getEntityClass());
        Root<E> root = cr.from(getEntityClass());
        List<Predicate> predicates = new ArrayList<>();
        for(Map.Entry<String, Object> entry: attributes.entrySet()) {
            if(entry.getValue() == null){
                continue;
            }
            String attributeName = entry.getKey();
            Object attributeValue = entry.getValue();

            if (attributeValue instanceof String) {
                String stringValue = (String) attributeValue;
                createPredicateForString(stringAsLike, cb, root, predicates, attributeName, stringValue);
            } else {
                predicates.add(cb.equal(root.get(attributeName), attributeValue));
            }


        }

        cr.select(root).where(predicates.toArray(new Predicate[0]));
        return getEntityManager().createQuery(cr);
    }

    private void createPredicateForString(boolean stringAsLike, CriteriaBuilder cb, Root<E> root, List<Predicate> predicates, String attributeName, String stringValue) {
        if(stringValue.trim().isEmpty()){
            return;
        }
        if(stringAsLike) {
            predicates.add(cb.like(root.get(attributeName), "%" + stringValue + "%"));
        } else {
            predicates.add(cb.equal(root.get(attributeName), stringValue));
        }
    }

}
