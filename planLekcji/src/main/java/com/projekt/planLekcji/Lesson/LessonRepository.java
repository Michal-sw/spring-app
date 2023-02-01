package com.projekt.planLekcji.Lesson;

import com.projekt.planLekcji.Teacher.Speciality;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

interface LessonRepository extends CrudRepository<Lesson, String> {

    @Override
    <S extends Lesson> S save(S entity);

    @Override
    <S extends Lesson> Iterable<S> saveAll(Iterable<S> entities);

    @Override
    Optional<Lesson> findById(String s);

    @Override
    boolean existsById(String s);

    @Override
    @Query("Select m from Lesson m")
    Iterable<Lesson> findAll();

    @Query("Select m from Lesson m where m.teacher.speciality = :s")
    Iterable<Lesson> findAllOfSubject(Speciality s);

    @Override
    Iterable<Lesson> findAllById(Iterable<String> strings);

    @Override
    long count();

    @Override
    void deleteById(String s);

    @Override
    void delete(Lesson entity);

    @Override
    void deleteAllById(Iterable<? extends String> strings);

    @Override
    void deleteAll(Iterable<? extends Lesson> entities);

    @Override
    void deleteAll();
}
