package com.anzhelika.spring_boot_application.service;

import java.util.List;
import java.util.Optional;

public interface CommonService<T, Id> {
    List<T> findAll();

    Optional<T> findById(Id id);

    List<T> findByName(String name);

    T update(T t);

    void deleteById(Id id);
}