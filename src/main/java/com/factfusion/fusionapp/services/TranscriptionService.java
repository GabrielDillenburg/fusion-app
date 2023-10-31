package com.factfusion.fusionapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.theokanning.openai.audio.CreateTranscriptionRequest;
import com.theokanning.openai.service.OpenAiService;


@Service
public class TranscriptionService {

    private final OpenAiService openai;

    @Autowired
    public TranscriptionService(OpenAiService openai) {
        this.openai = openai;
    }

    public String transcribeAudio(String audioFilePath) throws Exception {
      CreateTranscriptionRequest createTranscriptionRequest = CreateTranscriptionRequest.builder()
                .model("whisper-1")
                .build();

      String text = openai.createTranscription(createTranscriptionRequest, audioFilePath).getText();
      return text;
    }
}
