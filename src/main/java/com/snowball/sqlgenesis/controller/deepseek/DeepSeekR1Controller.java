package com.snowball.sqlgenesis.controller.deepseek;

import io.github.pigmesh.ai.deepseek.core.DeepSeekClient;
import io.github.pigmesh.ai.deepseek.core.chat.ChatCompletionResponse;
import jakarta.annotation.Resource;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * @author lihui
 * @date 2025/02/17 17:15
 **/
@Validated
@RestController
@RequestMapping("/deepseek-r1-1.5b")
public class DeepSeekR1Controller {
    @Resource
    private DeepSeekClient deepSeekClient;

    @GetMapping(value = "/chat", produces = MediaType.TEXT_EVENT_STREAM_VALUE + ";charset=UTF-8")
    public Flux<ChatCompletionResponse> chat(String prompt) {
        return deepSeekClient.chatFluxCompletion(prompt);
    }
}
