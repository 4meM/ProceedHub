package com.mistysoft.proceedhub.modules.scholarship.application;

import com.mistysoft.proceedhub.modules.scholarship.domain.ScholarshipRepository;

import org.springframework.stereotype.Service;

@Service
public class DeleteScholarship {
    
    private final ScholarshipRepository scholarshipRepository;

    public DeleteScholarship(ScholarshipRepository scholarshipRepository) {
        this.scholarshipRepository = scholarshipRepository; 
    }

    public void execute(String id) { 
        scholarshipRepository.deleteById(id); 
    }
}
