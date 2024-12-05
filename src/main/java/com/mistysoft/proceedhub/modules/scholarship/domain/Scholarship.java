package com.mistysoft.proceedhub.modules.scholarship.domain;

import lombok.Getter;
import java.util.Set;
import java.time.ZonedDateTime;

@Getter
public class Scholarship {
    private final String id;
    private final String title;
    private final String description;
    private final ZonedDateTime date;
    private final String image;
    private final String country;
    private final String continent;
    private final String moreInfo;
    private final Set<Requirement> requirements;

    public Scholarship(String id, String title, String description, ZonedDateTime date, String image, String country, String continent, String moreInfo, Set<Requirement> requirements) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.image = image;
        this.country = country;
        this.continent = continent;
        this.moreInfo = moreInfo;
        this.requirements = requirements;
    }
}