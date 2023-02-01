package com.projekt.planLekcji.Teacher;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

interface TeacherRepository extends CrudRepository<Teacher, String> {

    @Override
    <S extends Teacher> S save(S entity);

    @Override
    <S extends Teacher> Iterable<S> saveAll(Iterable<S> entities);

    @Override
    Optional<Teacher> findById(String s);

    @Override
    boolean existsById(String s);

    @Override
    @Query("Select m from Teacher m")
    Iterable<Teacher> findAll();

    @Query("Select m.speciality from Teacher m group by m.speciality")
    Iterable<Speciality> findAllTeacherSubjects();

    @Override
    Iterable<Teacher> findAllById(Iterable<String> strings);

    @Query("select m from Teacher m left join fetch m.lessons where m.id = :s ")
    Optional<List<Teacher>> findByIdWithLessons(String s);

    @Override
    long count();

    @Override
    void deleteById(String s);

    @Override
    void delete(Teacher entity);

    @Override
    void deleteAllById(Iterable<? extends String> strings);

    @Override
    void deleteAll(Iterable<? extends Teacher> entities);

    @Override
    void deleteAll();
}
