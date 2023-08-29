package com.estating.opportunity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PropertyTeaserDTO(
        @JsonProperty("_id")
        String id,
        String name
) {
}
