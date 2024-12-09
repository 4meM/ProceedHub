package com.mistysoft.proceedhub.apps.backend;

import com.mistysoft.proceedhub.modules.scholarship.application.*;
import com.mistysoft.proceedhub.modules.scholarship.application.dto.ScholarshipDTO;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/scholarships")
public class ScholarshipController {

    private final CreateScholarship createScholarship;
    private final UpdateScholarship updateScholarship;
    private final GetAllScholarships getAllScholarships;

    public ScholarshipController(CreateScholarship createScholarship, UpdateScholarship updateScholarship, GetAllScholarships getAllScholarships) {
        this.createScholarship = createScholarship;
        this.updateScholarship = updateScholarship;
        this.getAllScholarships = getAllScholarships;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createScholarship(@RequestBody ScholarshipDTO scholarship) {
        createScholarship.execute(scholarship);
        return new ResponseEntity<>("Scholarship created successfully", HttpStatus.CREATED);
    }

    @PostMapping("/get_by_id")
    public ResponseEntity<String> getScholarshipById(@RequestBody String id) {
        // getScholarshipById.execute(id);
        return new ResponseEntity<>("Scholarship retrieved successfully", HttpStatus.OK);
    }

    @GetMapping("/get_all")
    public ResponseEntity<String> getAllScholarships() {
        getAllScholarships.execute();
        return new ResponseEntity<>("Scholarships retrieved successfully", HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateScholarship(@RequestBody ScholarshipDTO scholarship){
        updateScholarship.execute(scholarship);
        return new ResponseEntity<>("Scholarship updated successfully", HttpStatus.OK);
    }
}
