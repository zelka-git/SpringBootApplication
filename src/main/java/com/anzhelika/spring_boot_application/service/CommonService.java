package com.anzhelika.spring_boot_application.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommonService<T, Id> {
    Page<T> findAll(Pageable pageable);

    T findById(Id id);

    List<T> findByName(String name);

    T update(T t);

    void deleteById(Id id);
}