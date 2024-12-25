package com.mistysoft.proceedhub.modules.scholarship.application;

import com.mistysoft.proceedhub.modules.scholarship.domain.IScholarshipRepository;
import com.mistysoft.proceedhub.modules.scholarship.domain.Scholarship;
import com.mistysoft.proceedhub.modules.scholarship.application.dto.ScholarshipDTO;
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

class UpdateScholarshipTest {

    @Mock
    private IScholarshipRepository scholarshipRepository;

    @InjectMocks
    private UpdateScholarship updateScholarship;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUpdateScholarshipFound() {
        String id = UUID.randomUUID().toString();
        Scholarship existingScholarship = Scholarship.builder()
                .id(id)
                .title("Old Title")
                .description("Old Description")
                .date(ZonedDateTime.now().minusDays(1))
                .image("Old Image")
                .country("Old Country")
                .continent("Old Continent")
                .moreInfo("Old More Info")
                .requirements(Set.of())
                .build();

        ScholarshipDTO request = ScholarshipDTO.builder()
                .title("New Title")
                .description("New Description")
                .date(ZonedDateTime.now())
                .image("New Image")
                .country("New Country")
                .continent("New Continent")
                .moreInfo("New More Info")
                .requirements(Set.of())
                .build();

        when(scholarshipRepository.findById(id)).thenReturn(Optional.of(existingScholarship));

        Scholarship result = updateScholarship.execute(request, id);

        assertEquals(request.getTitle(), result.getTitle());
        assertEquals(request.getDescription(), result.getDescription());
        assertEquals(request.getDate(), result.getDate());
        assertEquals(request.getImage(), result.getImage());
        assertEquals(request.getCountry(), result.getCountry());
        assertEquals(request.getContinent(), result.getContinent());
        assertEquals(request.getMoreInfo(), result.getMoreInfo());
        assertEquals(request.getRequirements(), result.getRequirements());
    }

    @Test
    void testUpdateScholarshipNotFound() {
        String id = UUID.randomUUID().toString();
        ScholarshipDTO request = ScholarshipDTO.builder().build();
        when(scholarshipRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> updateScholarship.execute(request, id));
    }
}