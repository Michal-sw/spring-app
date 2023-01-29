package com.projekt.planLekcji.Timetable;

import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class TimetableService {

    final TimetableRepository timetableRepository;

    public TimetableService(TimetableRepository timetableRepository) {
        this.timetableRepository = timetableRepository;
    }

    public Timetable addTimetable(Timetable timetableToAdd) {
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

    public Iterable<Timetable> getAllTimetables() {
        return timetableRepository.findAll();
    }

    public void deleteById(String id) {
        timetableRepository.deleteById(id);
    }
}
