package assemblyApi;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import com.google.gson.Gson;

public class TranscriptController {

    private Transcript transcript;

    public TranscriptController() {
        transcript = new Transcript();
    }

    // POST
    public void postTranscript() throws Exception {
        transcript.setAudio_url("https://www.pacdv.com/sounds/voices/maybe-next-time-huh.wav");
        Gson gson = new Gson();
        String jsonRequest = gson.toJson(transcript);

        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(new URI("https://api.assemblyai.com/v2/transcript"))
                .header("Authorization", "6398e70dfa8e41728fb05973b3918357")
                .POST(BodyPublishers.ofString(jsonRequest))
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> postResponse = httpClient.send(postRequest, BodyHandlers.ofString());

        transcript = gson.fromJson(postResponse.body(), Transcript.class);
        System.out.println(transcript.getId());
    }

    // GET
    public String getTranscriptStatus() throws Exception {
        HttpClient httpClient = HttpClient.newHttpClient();
        Gson gson = new Gson();
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI("https://api.assemblyai.com/v2/transcript/" + transcript.getId()))
                .header("Authorization", "6398e70dfa8e41728fb05973b3918357")
                .GET()
                .build();

        while (true) {
            HttpResponse<String> getResponse = httpClient.send(getRequest, BodyHandlers.ofString());
            transcript = gson.fromJson(getResponse.body(), Transcript.class);
            System.out.println(transcript.getStatus());
            if ("completed".equals(transcript.getStatus()) || "error".equals(transcript.getStatus())) {
                break;
            }
            Thread.sleep(1000);
        }

        System.out.println("Transcription completed!");
        return transcript.getText();
    }


}
