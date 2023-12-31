package com.servifacil.backendapp.library.util;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface JpaCustomRepository<T , ID> extends JpaRepository<T, ID> {

    // Only fetch non-deleted entities
    @Query("SELECT e FROM #{#entityName} e WHERE e.deleted_at IS NULL ")
    @Override
    List<T> findAll();

    // Only fetch entity by ID when it has not been soft-deleted
    @Query("SELECT e FROM #{#entityName} e WHERE e.deleted_at IS NULL AND e.id = ?1")
    @Override
    Optional<T> findById(ID id);

    // Only fetch the entities which have been soft deleted
    @Query("SELECT e FROM #{#entityName} e WHERE e.deleted_at IS NULL")
    List<T> findAllDeleted();

    // Update deleted property instead of deleting
    @Modifying
    @Query("UPDATE #{#entityName} e SET e.deleted_at = NOW() WHERE e.id = ?1")
    @Override
    void deleteById(ID id);


    // Update deleted properties instead of deleting
    @Modifying
    @Query("UPDATE #{#entityName} e SET e.deleted_at = NOW() WHERE e.id IN ?1")
    @Override
    void deleteAllById(Iterable<? extends ID> ids);

}

