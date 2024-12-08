package com.mistysoft.proceedhub.modules.scholarship.application.dto;

import com.mistysoft.proceedhub.modules.scholarship.domain.Requirement;
import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;
import java.util.Set;

@Getter
@Builder
public class CreateScholarshipDTO {
    private final String title;
    private final String description;
    private final ZonedDateTime date;
    private final String image;
    private final String country;
    private final String continent;
    private final String moreInfo;
    private final Set<Requirement> requirements;
}
