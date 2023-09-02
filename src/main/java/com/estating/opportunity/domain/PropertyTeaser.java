package com.estating.opportunity.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class PropertyTeaser {

    @JsonProperty("_id")
    private String id;

    private String name;

    private String internalName;

    private Options options;

    private List<AdditionalValue> summary;

    private SecurityAndRoles securityAndRoles;

}
