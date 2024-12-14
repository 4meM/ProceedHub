package com.mistysoft.proceedhub.modules.scholarship.application;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mistysoft.proceedhub.modules.scholarship.domain.IScholarshipRepository;
import com.mistysoft.proceedhub.modules.scholarship.application.dto.ScholarshipDTO;
import com.mistysoft.proceedhub.modules.scholarship.domain.Scholarship;

@Service
public class UpdateScholarship {
    
    private final IScholarshipRepository scholarshipRepository;

    public UpdateScholarship(IScholarshipRepository scholarshipRepository) {
        this.scholarshipRepository = scholarshipRepository;
    }

    public Scholarship execute(ScholarshipDTO request, String id) {
        Optional<Scholarship> updatedScholarship =  scholarshipRepository.findById(id);
        if( updatedScholarship.isEmpty() ){
            throw new IllegalArgumentException("Scholarship with this id does not exist");
        }

        Scholarship beforeScholarship = updatedScholarship.get();
        Scholarship scholarship = Scholarship.builder()
            .id(id)
            .title(request.getTitle() != null && !request.getTitle().isBlank() ? request.getTitle() : beforeScholarship.getTitle())
            .description(request.getDescription() != null && !request.getDescription().isBlank() ? request.getDescription() : beforeScholarship.getDescription())
            .date(request.getDate() != null ? request.getDate() : beforeScholarship.getDate())
            .image(request.getImage() != null && !request.getImage().isBlank() ? request.getImage() : beforeScholarship.getImage())
            .country(request.getCountry() != null && !request.getCountry().isBlank() ? request.getCountry() : beforeScholarship.getCountry())
            .continent(request.getContinent() != null && !request.getContinent().isBlank() ? request.getContinent() : beforeScholarship.getContinent())
            .moreInfo(request.getMoreInfo() != null && !request.getMoreInfo().isBlank() ? request.getMoreInfo() : beforeScholarship.getMoreInfo())
            .requirements(request.getRequirements() != null ? request.getRequirements() : beforeScholarship.getRequirements())
            .build();

        scholarshipRepository.save(scholarship);
        return scholarship;
    }
}
