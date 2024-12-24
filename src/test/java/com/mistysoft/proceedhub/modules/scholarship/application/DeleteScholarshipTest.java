package com.mistysoft.proceedhub.modules.scholarship.application;

import com.mistysoft.proceedhub.modules.scholarship.domain.IScholarshipRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

class DeleteScholarshipTest {

    @Mock
    private IScholarshipRepository scholarshipRepository;

    @InjectMocks
    private DeleteScholarship deleteScholarship;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDeleteScholarship() {
        String id = "test-id";

        deleteScholarship.execute(id);

        verify(scholarshipRepository).deleteById(id);
    }
}