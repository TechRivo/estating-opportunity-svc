package com.estating.opportunity.client;

import com.estating.opportunity.dto.PropertyTeaserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@FeignClient(value = "estatingApi", url = "${estating.api.url}", path = "/api/v1/admin")
public interface EstatingApiClient {

    @RequestMapping(method = GET, value = "/propertyTeaser")
    List<PropertyTeaserDTO> getPropertyTeasers();

}
