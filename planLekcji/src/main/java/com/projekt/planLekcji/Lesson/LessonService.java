package com.projekt.planLekcji.Lesson;

import com.projekt.planLekcji.Lesson.Lesson;
import com.projekt.planLekcji.Lesson.LessonRepository;
import com.projekt.planLekcji.Student.Student;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LessonService {

    final LessonRepository lessonRepository;

    public LessonService(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    public Lesson addLesson(Lesson lessonToAdd) {
        lessonRepository.save(lessonToAdd);
        return lessonToAdd;
    }

    public Lesson editLesson(Lesson lesson) {
        lessonRepository.save(lesson);
        return lesson;
    }

    public Lesson findById(String id) {
        Optional<Lesson> lesson =  lessonRepository.findById(id);
        return lesson.orElse(null);
    }

    public Iterable<Lesson> getAllLessons() {
        return lessonRepository.findAll();
    }

    public void deleteById(String id) {
        lessonRepository.deleteById(id);
    }
}
