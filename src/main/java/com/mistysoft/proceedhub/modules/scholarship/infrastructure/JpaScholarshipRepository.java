package com.mistysoft.proceedhub.modules.scholarship.infrastructure;

import com.mistysoft.proceedhub.modules.scholarship.domain.*;
import org.springframework.stereotype.Repository;

import java.util.Optional; 

@Repository 
public class JpaScholarshipRepository implements ScholarshipRepository {
    
    private final SpringDataScholarshipRepository repository;

    public JpaScholarshipRepository(SpringDataScholarshipRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Scholarship scholarship) {
        ScholarshipEntity entity = ScholarshipMapper.toEntity(scholarship);
        repository.save(entity);
    }

    @Override
    public Optional<Scholarship> findById(String id) {
        return repository.findById(id)
                .map(ScholarshipMapper::toDomain);
    }
}
