package com.mistysoft.proceedhub.modules.scholarship.application;

import com.mistysoft.proceedhub.modules.scholarship.domain.*;

import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Set;
import java.util.UUID;

@Service
public class CreateScholarship {

    private final ScholarshipRepository scholarshipRepository;

    public CreateScholarship(ScholarshipRepository scholarshipRepository) {
        this.scholarshipRepository = scholarshipRepository;
    }

    public Scholarship execute(String title, String description, ZonedDateTime date, String image, String country, String continent, String moreInfo, Set<Requirement> requirements) {
        String id = UUID.randomUUID().toString();
        Scholarship scholarship = new Scholarship(id, title, description, date, image, country, continent, moreInfo, requirements);
        scholarshipRepository.save(scholarship);
        return scholarship;
    }
}
