package com.mistysoft.proceedhub.modules.scholarship.domain;

import java.util.Optional;

public interface ScholarshipRepository {
    void save(Scholarship scholarship);
    Optional<Scholarship> findById(String id);  
} 