package com.estating.opportunity.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdditionalValue {

    private String name;

    private String value;

    private String tooltip;

}
