package com.mistysoft.proceedhub.modules.scholarship.application;

import com.mistysoft.proceedhub.modules.scholarship.domain.IScholarshipRepository;
import com.mistysoft.proceedhub.modules.scholarship.domain.Scholarship;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class SearchScholarshipTest {

    @Mock
    private IScholarshipRepository scholarshipRepository;

    @InjectMocks
    private SearchScholarship searchScholarship;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSearchScholarshipFound() {
        String id = UUID.randomUUID().toString();
        Scholarship scholarship = Scholarship.builder()
                .id(id)
                .title("Title 1")
                .description("Description 1")
                .date(ZonedDateTime.now())
                .image("Image 1")
                .country("Country 1")
                .continent("Continent 1")
                .moreInfo("More Info 1")
                .requirements(Set.of())
                .build();
        when(scholarshipRepository.findById(id)).thenReturn(Optional.of(scholarship));

        Scholarship result = searchScholarship.execute(id);

        assertEquals(scholarship, result);
    }

    @Test
    void testSearchScholarshipNotFound() {
        String id = UUID.randomUUID().toString();
        when(scholarshipRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> searchScholarship.execute(id));
    }
}