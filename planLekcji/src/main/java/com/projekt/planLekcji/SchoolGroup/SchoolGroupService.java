package com.projekt.planLekcji.SchoolGroup;

import com.projekt.planLekcji.Student.Student;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SchoolGroupService {

    final SchoolGroupRepository schoolGroupRepository;

    public SchoolGroupService(SchoolGroupRepository schoolGroupRepository) {
        this.schoolGroupRepository = schoolGroupRepository;
    }

    private void fillData() {
    }

    public SchoolGroup addSchoolGroup(SchoolGroup schoolGroupToAdd) {
        schoolGroupRepository.save(schoolGroupToAdd);
        return schoolGroupToAdd;
    }

    public SchoolGroup editSchoolGroup(SchoolGroup schoolGroup) {
        schoolGroupRepository.save(schoolGroup);
        return schoolGroup;
    }

    public SchoolGroup deleteStudentFromGroup(Student student) {
        Optional<SchoolGroup> schoolGroupOptional = schoolGroupRepository.findById(student.getSchoolGroup().getId());
        if (schoolGroupOptional.isPresent()) {
            SchoolGroup schoolGroup = schoolGroupOptional.get();
            schoolGroup.removeStudent(student);
            schoolGroupRepository.save(schoolGroup);
            return schoolGroup;
        }
        return null;
    }

    public SchoolGroup findById(String id) {
        Optional<SchoolGroup> schoolGroup =  schoolGroupRepository.findById(id);
        return schoolGroup.orElse(null);
    }

    public SchoolGroup findByIdWithStudents(String id) {
        Optional<List<SchoolGroup>> schoolGroups =  schoolGroupRepository.findByIdWithStudents(id);
        if (schoolGroups.isPresent()) {
            return schoolGroups.get().get(0);
        }
        return null;
    }

    public Iterable<SchoolGroup> getAllSchoolGroups() {
        return schoolGroupRepository.findAll();
    }

    public void deleteById(String id) {
        schoolGroupRepository.deleteById(id);
    }
}
