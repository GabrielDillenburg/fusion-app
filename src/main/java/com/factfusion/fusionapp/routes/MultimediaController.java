package com.factfusion.fusionapp.routes;

import org.springframework.web.bind.annotation.*;
import java.io.File; // Import the File class
import com.factfusion.fusionapp.services.TranscriptionService;

@RestController
@RequestMapping("/api")
public class MultimediaController {

  private final TranscriptionService transcriptionService;

  public MultimediaController(TranscriptionService transcriptionService) {
    this.transcriptionService = transcriptionService;
  }

  public static class YouTubeRequest {
    private String youtubeUrl;

    // Getter and setter for youtubeUrl

    public String getYoutubeUrl() {
        return youtubeUrl;
    }

    public void setYoutubeUrl(String youtubeUrl) {
        this.youtubeUrl = youtubeUrl;
    }
  }


  @PostMapping("/transcribe")
  public String transcribeVideo(@RequestBody YouTubeRequest youTubeRequest) {

    String youtubeUrl = youTubeRequest.getYoutubeUrl();

    System.out.println(youtubeUrl);
    System.out.println(System.currentTimeMillis());
    try {
      // Download the YouTube video using youtube-dl (assuming it's installed)
      String[] downloadCmd = {
          "yt-dlp",
          "--extract-audio",
          "--audio-format",
          "mp3",
          "-o",
          "testAudio.mp3",
          "-P",
          "../data/",
          youtubeUrl
      };

      Process downloadProcess = new ProcessBuilder(downloadCmd).start();
      downloadProcess.waitFor();

      System.out.println(System.currentTimeMillis());


      // Check if the download was successful
      if (downloadProcess.exitValue() != 0) {
        return "Video download failed.";
      }

      System.out.println(System.currentTimeMillis());
      String text = transcriptionService.transcribeAudio("../data/testAudio.mp3");
      System.out.println(System.currentTimeMillis());

      return text;
    } catch (Exception e) {
      e.printStackTrace();
      return "An error occurred during processing.";
    }
  }
}
