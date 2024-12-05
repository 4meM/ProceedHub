package com.mistysoft.proceedhub.modules.scholarship.infrastructure;

import com.mistysoft.proceedhub.modules.scholarship.domain.*;
import org.springframework.stereotype.Repository;


@Repository 
public class JpaScholarshipRepository {
    private final SpringDataScholarshipRepository repository;

    public JpaScholarshipRepository(SpringDataScholarshipRepository repository) {
        this.repository = repository;
    }

    public void save(Scholarship scholarship) {
        ScholarshipEntity entity = ScholarshipMapper.toEntity(scholarship);
        repository.save(entity);
    }
}
