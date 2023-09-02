package com.estating.opportunity.client;

import com.estating.opportunity.domain.PropertyTeaser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@FeignClient(value = "estatingApi", url = "${estating.api.url}", path = "/api/v1/admin")
public interface EstatingApiClient {

    @RequestMapping(method = GET, value = "/propertyTeaser")
    List<PropertyTeaser> getPropertyTeasers(@RequestParam String internalName);

    @RequestMapping(method = GET, value = "/propertyTeaser/{teaserId}")
    PropertyTeaser getById(@PathVariable String teaserId);
}
