package ro.agitman.moe.dao.impl;

import org.mentabean.BeanSession;
import org.mentabean.util.OrderBy;
import ro.agitman.moe.dao.GenericDao;
import static org.mentabean.util.SQLUtils.*;

import java.util.Collections;
import java.util.List;

/**
 * Created by d-uu31cq on 07.07.2016.
 */
public abstract class GenericDaoImpl<T> implements GenericDao<T> {

    private Class<T> entityClass;
    protected final BeanSession beanSession;

    /**
     *
     * @param clazzToSet .
     * @param beanSession .
     */
    public GenericDaoImpl(Class<T> clazzToSet, BeanSession beanSession) {
        this.entityClass = clazzToSet;
        this.beanSession = beanSession;
    }

    public List<T> findAll(){
        try {
            return beanSession.loadList(entityClass.newInstance(), orderByAsc("id"));
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IllegalStateException("Cannot load list ", e);
        }
    }

    @Override
    public T insert(T object) {
        beanSession.insert(object);
        return object;
    }

    @Override
    public T save(T object) {
        beanSession.update(object);
        return object;
    }

    @Override
    public T update(T object) {
        int modified = beanSession.update(object);
        if (modified != 1) {
            throw new IllegalStateException("Cannot update object " + object);
        }
        return object;
    }

    @Override
    public void delete(T object){
        boolean deleted = beanSession.delete(object);
        if (!deleted) {
            throw new IllegalStateException("Cannot delete object " + object);
        }
    }

}
