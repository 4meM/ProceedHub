package com.mistysoft.proceedhub.modules.scholarship.application;

import com.mistysoft.proceedhub.modules.scholarship.domain.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GetAllScholarships {
    
    private final IScholarshipRepository scholarshipRepository;

    public GetAllScholarships(IScholarshipRepository scholarshipRepository) {
        this.scholarshipRepository = scholarshipRepository;
    }

    public List<Scholarship> execute() {
        return scholarshipRepository.findAll();
    }
}
