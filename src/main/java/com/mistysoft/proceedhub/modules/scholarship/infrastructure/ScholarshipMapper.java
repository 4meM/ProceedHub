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
        return new Scholarship(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getDate(),
                entity.getImage(),
                entity.getCountry(),
                entity.getContinent(),
                entity.getMoreInfo(),
                entity.getRequirements()
        );
    }

}
