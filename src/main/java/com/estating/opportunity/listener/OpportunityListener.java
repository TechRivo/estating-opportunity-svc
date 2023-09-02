package com.estating.opportunity.listener;

import com.estating.opportunity.dto.SaveRequest;
import com.estating.opportunity.service.OpportunityService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OpportunityListener {

    private final OpportunityService service;
    private final RabbitTemplate template;

    @RabbitListener(queues = {"handle.plan.command"})
    public void onPlanCommand(SaveRequest req){
        var id = service.getIdByInternalName(req.getInternalName());
        req.setId(id);
        try {
            var resp = service.planSave(req);
            template.convertAndSend("goldensheet","goldensheet.event.validation.succeeded",resp,msg -> {
                msg.getMessageProperties().setCorrelationId("xyz");
                return msg;
            });
        } catch (JsonProcessingException e) {
            template.convertAndSend("goldensheet","goldensheet.event.validation.failed","goldensheet.event.validation.failed",msg -> {
                msg.getMessageProperties().setCorrelationId("xyz");
                return msg;
            });
        }

    }

}
