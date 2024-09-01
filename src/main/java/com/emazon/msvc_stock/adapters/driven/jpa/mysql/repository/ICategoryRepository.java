package com.emazon.msvc_stock.adapters.driven.jpa.mysql.repository;

import com.emazon.msvc_stock.adapters.driven.jpa.mysql.entity.CategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<CategoryEntity, Long> {
    boolean existsByName(String name);
    CategoryEntity findByName(String name);
    Page<CategoryEntity> findAll(Pageable pageable);

}
