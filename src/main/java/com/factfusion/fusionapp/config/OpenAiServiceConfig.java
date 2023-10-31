package com.factfusion.fusionapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.theokanning.openai.service.OpenAiService;

@Configuration
public class OpenAiServiceConfig {
    @Bean
    public OpenAiService openAiService() {
        return new OpenAiService("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"); // You may need to configure this service if it requires parameters.
    }
}

