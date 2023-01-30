package com.projekt.planLekcji.SchoolGroup;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface SchoolGroupRepository extends CrudRepository<SchoolGroup, String> {

    @Override
    <S extends SchoolGroup> S save(S entity);

    @Override
    <S extends SchoolGroup> Iterable<S> saveAll(Iterable<S> entities);

    @Override
    Optional<SchoolGroup> findById(String s);

    @Query("select m from SchoolGroup m where m.name = :s ")
    Optional<SchoolGroup> findByName(String s);

    @Query("select m from SchoolGroup m left join fetch m.students where m.id = :s ")
    Optional<List<SchoolGroup>> findByIdWithStudents(String s);

    @Override
    boolean existsById(String s);

    @Override
    @Query("Select m from SchoolGroup m")
    Iterable<SchoolGroup> findAll();

    @Override
    Iterable<SchoolGroup> findAllById(Iterable<String> strings);

    @Override
    long count();

    @Override
    void deleteById(String s);

    @Override
    void delete(SchoolGroup entity);

    @Override
    void deleteAllById(Iterable<? extends String> strings);

    @Override
    void deleteAll(Iterable<? extends SchoolGroup> entities);

    @Override
    void deleteAll();
}
