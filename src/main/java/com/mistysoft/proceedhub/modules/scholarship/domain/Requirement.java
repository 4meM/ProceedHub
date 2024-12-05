package com.mistysoft.proceedhub.modules.scholarship.domain;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class Requirement {
    private String name;
}
