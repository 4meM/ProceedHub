package com.mistysoft.proceedhub.modules.scholarship.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataScholarshipRepository extends JpaRepository<ScholarshipEntity, String> {
    Optional<ScholarshipEntity> findById(String id);
    void deleteById(String id);
} 