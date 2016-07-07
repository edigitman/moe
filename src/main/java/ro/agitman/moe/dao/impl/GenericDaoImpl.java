package ro.agitman.moe.dao.impl;

import org.mentabean.BeanSession;
import ro.agitman.moe.dao.GenericDao;

/**
 * Created by d-uu31cq on 07.07.2016.
 */
public abstract class GenericDaoImpl<T> implements GenericDao<T> {

    private Class<T> entityClass;
    protected final BeanSession beanSession;

    public GenericDaoImpl(Class<T> clazzToSet, BeanSession beanSession) {
        this.entityClass = clazzToSet;
        this.beanSession = beanSession;
    }

    @Override
    public T insert(T object) {
        return null;
    }
}
