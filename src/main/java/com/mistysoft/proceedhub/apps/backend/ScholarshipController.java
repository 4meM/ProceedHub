package com.mistysoft.proceedhub.apps.backend;

import com.mistysoft.proceedhub.modules.scholarship.application.CreateScholarship;
import com.mistysoft.proceedhub.modules.scholarship.application.dto.CreateScholarshipDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/scholarships")
public class ScholarshipController {
    private final CreateScholarship createScholarship;
    public ScholarshipController(CreateScholarship createScholarship) {
        this.createScholarship = createScholarship;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createScholarship(@RequestBody CreateScholarshipDTO scholarship) {
        createScholarship.execute(scholarship);
        return new ResponseEntity<>("Scholarship created successfully", HttpStatus.CREATED);
    }
}
