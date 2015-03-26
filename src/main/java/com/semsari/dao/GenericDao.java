package com.semsari.dao;

import java.util.List;

public interface GenericDao< T > {


    T create(T t);

    void delete(Object id);

    T find(int id);

    T update(T t);
    
    Integer save(T t);
    
    List<T> findAll();
}
