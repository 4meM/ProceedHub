package com.mistysoft.proceedhub.modules.scholarship.application;

import com.mistysoft.proceedhub.modules.scholarship.domain.IScholarshipRepository;

import org.springframework.stereotype.Service;

@Service
public class DeleteScholarship {
    
    private final IScholarshipRepository scholarshipRepository;

    public DeleteScholarship(IScholarshipRepository scholarshipRepository) {
        this.scholarshipRepository = scholarshipRepository; 
    }

    public void execute(String id) { 
        scholarshipRepository.deleteById(id); 
    }
}
