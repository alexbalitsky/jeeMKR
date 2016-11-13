package dao;


import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.mapping.Array;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.*;
import javax.persistence.criteria.*;
import java.util.*;

/**
 * Created by obalitskyi on 10/4/16.
 */
public abstract class AbstractDAO<T> {
    @PersistenceContext
    protected EntityManager em;

    private Class<T> entityClass;

    public AbstractDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public void save(T entity) {
        em.persist(entity);
    }

    public void delete(T entity) {
        T entityToBeRemoved = em.merge(entity);
        em.remove(entityToBeRemoved);

    }

    public void delete(Long entityID){
        T entityToBeRemoved = find(entityID);
        delete(entityToBeRemoved);
    }

    public T update(T entity) {
        return em.merge(entity);
    }

    public T find(Long entityID) {
        return em.find(entityClass, entityID);
    }

    public T find(String primaryKey){
        return em.find(entityClass, primaryKey);
    }

    public T merge(T entity){
        return em.merge(entity);
    }

    public Set<T> findAll() {
        Set<T> result = new HashSet<T>();
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        result.addAll(em.createQuery(cq).getResultList());
        return result;
    }

    public Set<T> getByIDs(List<String> ids) {
        Set<T> result = new HashSet<>();
        for (String id : ids) {
            result.add(find(Long.valueOf(id)));
        }
        return result;
    }
}
