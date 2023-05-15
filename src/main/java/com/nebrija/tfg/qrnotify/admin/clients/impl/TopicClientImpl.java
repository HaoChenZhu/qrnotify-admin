package com.nebrija.tfg.qrnotify.admin.clients.impl;

import com.nebrija.tfg.qrnotify.admin.clients.TopicClient;
import com.nebrija.tfg.qrnotify.admin.models.ApiTopicResponseDto;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TopicClientImpl {

    @Autowired
    TopicClient topicClient;

    public boolean existTopic(String topicId) {
        try {
            ApiTopicResponseDto topic = topicClient.getTopic(topicId);
            if(topic == null){
                return false;
            }
            return true;
        }catch (FeignException e){
            log.error("Status code: "+e.status());
        }catch (Exception e){
            log.error("Error: "+e.getMessage());
        }
        return false;
    }
}
