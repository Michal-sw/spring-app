package com.projekt.planLekcji.Timetable;

import com.projekt.planLekcji.Lesson.Lesson;
import com.projekt.planLekcji.Lesson.LessonService;
import com.projekt.planLekcji.SchoolGroup.SchoolGroup;
import com.projekt.planLekcji.Teacher.Teacher;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TimetableService {

    final TimetableRepository timetableRepository;

    public TimetableService(TimetableRepository timetableRepository) {
        this.timetableRepository = timetableRepository;
    }

    public Timetable addTimetable(Timetable timetableToAdd) {
        for (Lesson lesson : timetableToAdd.getLessons()) {
            lesson.setTimetable(timetableToAdd);
        }
        timetableRepository.save(timetableToAdd);
        return timetableToAdd;
    }

    public Timetable editTimetable(Timetable timetable) {
        timetableRepository.save(timetable);
        return timetable;
    }

    public Timetable findById(String id) {
        Optional<Timetable> timetable =  timetableRepository.findById(id);
        return timetable.orElse(null);
    }

    public Timetable findByIdWithAllLessons(String id) {
        Optional<List<Timetable>> timetables =  timetableRepository.findByIdWithLessons(id);
        if (timetables.isPresent()) {
            return timetables.get().get(0);
        }
        return null;
    }

    public Iterable<SchoolGroup> findAllGroupsWithoutTimetable() {
        Iterable<SchoolGroup> schoolGroups = timetableRepository.findAllGroupsWithoutTimetable();
        return schoolGroups;
    }

    public Iterable<Timetable> getAllTimetables() {
        return timetableRepository.findAll();
    }

    public void deleteById(String id) {
        timetableRepository.deleteById(id);
    }
}
