package com.lucas.gp;

import java.util.List;

/**
 *
 * @author lucas
 */
public abstract class GenericDao<T> extends PersistenceUtil {
    private Class<T> entityClass;
    
    public void commit() {
        getEntityManager().getTransaction().commit();
    }

    public void create(T entity) {
        getEntityManager().getTransaction().begin();
        getEntityManager().persist(entity);
        getEntityManager().flush();
    }
    public void save() {
        getEntityManager().getTransaction().begin();
        getEntityManager().persist(this);
        getEntityManager().flush();
        getEntityManager().getTransaction().commit();
    }

    public void edit(T entity) {
        getEntityManager().getTransaction().begin();
        getEntityManager().merge(entity);
        getEntityManager().flush();
    }
    
    public void edit() {
        getEntityManager().getTransaction().begin();
        getEntityManager().merge(this);
        getEntityManager().flush();
        getEntityManager().getTransaction().commit();
    }

    public void remove() {
        getEntityManager().remove(getEntityManager().merge(this));
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    
}
