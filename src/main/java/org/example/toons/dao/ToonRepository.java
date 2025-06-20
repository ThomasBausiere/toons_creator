package org.example.toons.dao;

import org.example.toons.model.Toon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ToonRepository extends JpaRepository<Toon, UUID> {

    @Query("SELECT t FROM Toon t WHERE " +
            "LOWER(t.name) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(t.campaign) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(t.profession) LIKE LOWER(CONCAT('%', :search, '%'))")
    List<Toon> searchToonsByKeyword(@Param("search") String search);

}
