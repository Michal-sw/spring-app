package com.projekt.planLekcji.SchoolGroup;
import com.projekt.planLekcji.SchoolGroup.*;

import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
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


    public Iterable<SchoolGroup> getAllGroups() {
        return schoolGroupRepository.findAll();
    }

    public SchoolGroup editSchoolGroup(SchoolGroup schoolGroup) {
        schoolGroupRepository.save(schoolGroup);
        return schoolGroup;
    }

    public SchoolGroup findById(String id) {
        Optional<SchoolGroup> schoolGroup =  schoolGroupRepository.findById(id);
        return schoolGroup.orElse(null);
    }

    public Iterable<SchoolGroup> getAllSchoolGroups() {
        return schoolGroupRepository.findAll();
    }

    public void deleteById(String id) {
        schoolGroupRepository.deleteById(id);
    }
}
