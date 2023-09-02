package com.estating.opportunity.service;

import com.estating.opportunity.domain.PropertyTeaser;
import com.estating.opportunity.domain.PropertyTeaserRepository;
import com.estating.opportunity.dto.SaveRequest;
import com.estating.opportunity.mapper.SaveRequestMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.json.Json;
import jakarta.json.JsonPatch;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.StringReader;

@Slf4j
@Service
@RequiredArgsConstructor
public class OpportunityService {

    private final PropertyTeaserRepository repo;
    private final ObjectMapper objectMapper;
    private final SaveRequestMapper saveRequestMapper;

    public String getIdByInternalName(String internalName) {
        return repo.getByInternalName(internalName)
                .stream()
                .findFirst()
                .map(PropertyTeaser::getId)
                .orElseThrow();
    }

    public JsonPatch planSave(SaveRequest save) throws JsonProcessingException {
        // convert property teaser into json object
        var target = repo.getById(save.getId());
        var targetStr = objectMapper.writeValueAsString(target);
        var targetJson = Json.createReader(new StringReader(targetStr)).readObject();

        // apply save onto property teaser and convert into json object
        var source = saveRequestMapper.mapToPropertyTeaser(save, target);
        var sourceStr = objectMapper.writeValueAsString(source);
        var sourceJson = Json.createReader(new StringReader(sourceStr)).readObject();

        // create json patch of both teasers
        return Json.createDiff(targetJson, sourceJson);
    }

}
