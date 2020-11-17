package com.anzhelika.spring_boot_application.service;

import java.util.List;

public interface CommonService<T, Id> {
    List<T> findAll();

    T findById(Id id);

    List<T> findByName(String name);

    T update(T t);

    void deleteById(Id id);
}