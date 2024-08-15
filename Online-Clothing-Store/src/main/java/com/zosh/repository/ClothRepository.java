package com.zosh.repository;

import com.zosh.model.Cloth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClothRepository extends JpaRepository<Cloth,Long> {

    @Query("SELECT f FROM Cloth f WHERE f.name LIKE %:keyword% OR f.clothCategory.name LIKE %:keyword%")
    List<Cloth>searchCloth();
}
