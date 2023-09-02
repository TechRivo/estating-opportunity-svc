package com.estating.opportunity.dto;

import com.estating.opportunity.domain.SubscriptionStatus;
import com.estating.opportunity.domain.TeaserStatus;
import lombok.Data;

@Data
public class SaveRequest {

    private String id;

    private String internalName;

    private SubscriptionStatus status;

    private TeaserStatus teaserStatus;

    private String soldPercentage;

    private String openValue;

    private String relincPrice;

}
