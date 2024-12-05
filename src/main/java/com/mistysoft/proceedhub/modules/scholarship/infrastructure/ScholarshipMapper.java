package com.mistysoft.proceedhub.modules.scholarship.infrastructure;

import com.mistysoft.proceedhub.modules.scholarship.domain.*;

public class ScholarshipMapper {
    
    public static ScholarshipEntity toEntity(Scholarship scholarship) {
        ScholarshipEntity entity = new ScholarshipEntity();
        entity.setId(scholarship.getId());
        entity.setTitle(scholarship.getTitle());
        entity.setDescription(scholarship.getDescription());
        entity.setDate(scholarship.getDate());
        entity.setImage(scholarship.getImage());
        entity.setCountry(scholarship.getCountry());
        entity.setContinent(scholarship.getContinent());
        entity.setMoreInfo(scholarship.getMoreInfo());
        entity.setRequirements(scholarship.getRequirements());        
        return entity;
    }

    public static Scholarship toDomain(ScholarshipEntity entity) {
        return Scholarship.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .date(entity.getDate())
                .image(entity.getImage())
                .country(entity.getCountry())
                .continent(entity.getContinent())
                .moreInfo(entity.getMoreInfo())
                .requirements(entity.getRequirements())
                .build();
    }

}
