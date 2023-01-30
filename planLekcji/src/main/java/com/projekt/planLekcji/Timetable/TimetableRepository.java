package com.projekt.planLekcji.Timetable;

import com.projekt.planLekcji.SchoolGroup.SchoolGroup;
import com.projekt.planLekcji.Teacher.Teacher;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.OrderBy;
import java.util.List;
import java.util.Optional;

interface TimetableRepository extends CrudRepository<Timetable, String> {

    @Override
    <S extends Timetable> S save(S entity);

    @Override
    <S extends Timetable> Iterable<S> saveAll(Iterable<S> entities);

    @Override
    Optional<Timetable> findById(String s);

    @Override
    boolean existsById(String s);

    @Override
    @Query("Select m from Timetable m")
    Iterable<Timetable> findAll();

    @Query("Select e from SchoolGroup e where e.id not in (select m.assignedToSchoolGroup.id from Timetable m)")
    Iterable<SchoolGroup> findAllGroupsWithoutTimetable();

    @Override
    Iterable<Timetable> findAllById(Iterable<String> strings);

    @Query("select m from Timetable m left join fetch m.lessons where m.id = :s")
    Optional<List<Timetable>>findByIdWithLessons(String s);

    @Query("select m from Timetable m left join fetch m.lessons where m.id = :s")
    @OrderBy("startDate asc")
    Optional<List<Timetable>> findByIdWithLessonsSortedByDate(String s);

    @Override
    long count();

    @Override
    void deleteById(String s);

    @Override
    void delete(Timetable entity);

    @Override
    void deleteAllById(Iterable<? extends String> strings);

    @Override
    void deleteAll(Iterable<? extends Timetable> entities);

    @Override
    void deleteAll();
}
