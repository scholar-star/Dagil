package busan_dining.dagil.services;

import busan_dining.dagil.entities.Restaurants;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.springframework.stereotype.Service;
import tools.jackson.databind.util.JSONPObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

@Service
public class SearchService {
    public List<Restaurants> searchRestaurants(String restaurantName)
    throws MalformedURLException, IOException, InterruptedException {
        String searchURL = "https://places.googleapis.com/v1/places:"+restaurantName;
        HttpResponse<String> response = getAPIResponse(searchURL);

        JSONArray array = new JSONArray(response.body());
    }

    private HttpResponse<String> getAPIResponse(String searchURL)
    throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient(); // 요청을 보낼 Client 생성

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(searchURL)) // 요청 URL
                .GET() // GET
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString()); // 응답 본문 문자열로 가지고 오기
        return response;
    }
}
