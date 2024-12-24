package com.mistysoft.proceedhub.modules.scholarship.infrastructure;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ISpringDataScholarshipRepository extends JpaRepository<ScholarshipEntity, String> {
    @EntityGraph(attributePaths = "requirements")
    List<ScholarshipEntity> findAll();
    Optional<ScholarshipEntity> findById(String id);
    void deleteById(String id);
} 