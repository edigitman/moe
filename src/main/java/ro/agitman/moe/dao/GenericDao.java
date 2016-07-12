package ro.agitman.moe.dao;

import java.util.List;

/**
 * Created by d-uu31cq on 07.07.2016.
 */
public interface GenericDao<T> {

    T findById(Integer id);

    List<T> findAll();

    T insert(T object);

    T save(T object);

    T update(T object);

    void delete(T object);
}
