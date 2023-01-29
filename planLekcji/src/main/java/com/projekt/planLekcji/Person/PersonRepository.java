package com.projekt.planLekcji.Person;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PersonRepository extends CrudRepository<Person, String> {

    @Override
    <S extends Person> S save(S entity);

    @Override
    <S extends Person> Iterable<S> saveAll(Iterable<S> entities);

    @Override
    Optional<Person> findById(String s);

    @Override
    boolean existsById(String s);

    @Override
    @Query("Select m from Person m")
    Iterable<Person> findAll();

    @Override
    Iterable<Person> findAllById(Iterable<String> strings);

    @Override
    long count();

    @Override
    void deleteById(String s);

    @Override
    void delete(Person entity);

    @Override
    void deleteAllById(Iterable<? extends String> strings);

    @Override
    void deleteAll(Iterable<? extends Person> entities);

    @Override
    void deleteAll();
}
