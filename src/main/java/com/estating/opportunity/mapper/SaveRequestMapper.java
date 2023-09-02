package com.estating.opportunity.mapper;

import com.estating.opportunity.domain.AdditionalValue;
import com.estating.opportunity.domain.PropertyTeaser;
import com.estating.opportunity.dto.SaveRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaveRequestMapper {

    public PropertyTeaser mapToPropertyTeaser(SaveRequest source, PropertyTeaser target) {
        if (target.getOptions() != null) {
            target.getOptions().setStatus(source.getStatus());
            target.getOptions().setTeaserStatus(source.getTeaserStatus());
        }

        if (target.getSummary() != null) {
            mapAdditionalValue(target.getSummary(), "Sold Percentage", source.getSoldPercentage());
            mapAdditionalValue(target.getSummary(), "Open Value", source.getOpenValue());
        }

        if (target.getSecurityAndRoles() != null && target.getSecurityAndRoles().getSecurity() != null) {
            mapAdditionalValue(target.getSecurityAndRoles().getSecurity(), "Price", source.getRelincPrice());
        }

        return target;
    }

    private void mapAdditionalValue(List<AdditionalValue> values, String name, String value) {
        values.stream()
                .filter(av -> StringUtils.containsIgnoreCase(av.getName(),name))
                .findFirst()
                .ifPresentOrElse(av -> av.setValue(value), () -> {
                    var av = new AdditionalValue(name,value,null);
                    values.add(av);
                });
    }
}
