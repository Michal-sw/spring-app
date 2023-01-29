package com.projekt.planLekcji.Student;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface StudentRepository extends CrudRepository<Student, String> {

    @Override
    <S extends Student> S save(S entity);

    @Override
    <S extends Student> Iterable<S> saveAll(Iterable<S> entities);

    @Override
    Optional<Student> findById(String s);

    @Override
    boolean existsById(String s);

    @Override
    @Query("Select m from Student m")
    Iterable<Student> findAll();

    @Override
    Iterable<Student> findAllById(Iterable<String> strings);

    @Override
    long count();

    @Override
    void deleteById(String s);

    @Override
    void delete(Student entity);

    @Override
    void deleteAllById(Iterable<? extends String> strings);

    @Override
    void deleteAll(Iterable<? extends Student> entities);

    @Override
    void deleteAll();
}
