package com.estating.opportunity.domain;

import com.estating.opportunity.client.EstatingApiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PropertyTeaserRepository {

    private final EstatingApiClient client;

    public List<PropertyTeaser> getByInternalName(String internalName) {
        return client.getPropertyTeasers(internalName);
    }

    public PropertyTeaser getById(String teaserId){
        return client.getById(teaserId);
    }

}
