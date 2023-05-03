package com.nebrija.tfg.qrnotify.admin.clients;

import com.nebrija.tfg.qrnotify.admin.config.FeignClientConfiguration;
import com.nebrija.tfg.qrnotify.admin.models.ApiTopicResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "qrnotify-topic", url = "${qrnotify.feign.topic}", configuration = FeignClientConfiguration.class)
public interface TopicClient {

    @GetMapping("/topic")
    List<ApiTopicResponseDto> getAllTopics();

    @GetMapping("/topic/{identifier}")
    ApiTopicResponseDto getTopic(@PathVariable("identifier") String identifier);
}
