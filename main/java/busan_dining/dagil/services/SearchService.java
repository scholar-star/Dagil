package busan_dining.dagil.services;

import busan_dining.dagil.entities.Restaurants;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jackson.autoconfigure.JacksonProperties;
import org.springframework.stereotype.Service;
import tools.jackson.databind.util.JSONPObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SearchService {
    @Value("${search.apiKey}")
    private String apiKey;

    @Value("${search.apiID}")
    private String apiID;

    public List<Restaurants> searchRestaurants(String restaurantName)
    throws MalformedURLException, IOException, InterruptedException {
        String searchURL = "https://openapi.naver.com/v1/search/local.json";
        HttpResponse<String> response = getAPIResponse(searchURL, restaurantName);

        JSONObject totalObject = new JSONObject(response.body());

        JSONArray items = totalObject.getJSONArray("items");

        List<Restaurants> restaurants = new ArrayList<>();
        for (int i = 0; i < items.length(); i++) {
            JSONObject item = (JSONObject) items.get(i);

            Integer mapx = item.getInt("mapx");
            Integer mapy = item.getInt("mapy");

            Float latitude = mapx/10000000F;
            Float longitude = mapy/10000000F;

            Restaurants restaurant = Restaurants.builder()
                    .name(item.getString("title"))
                    .latitude(latitude)
                    .longitude(longitude)
                    .build();

            restaurants.add(restaurant);
        }
        return restaurants;
    }

    private HttpResponse<String> getAPIResponse(String searchURL, String keyword)
    throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient(); // 요청을 보낼 Client 생성

        String totalURL = searchURL+"?query="+keyword;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(searchURL)) // 요청 URL
                .header("X-Naver-Client-Id", apiID)
                .header("X-Naver-Client-Secret", apiKey)
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString()); // 응답 본문 문자열로 가지고 오기
        return response;
    }
}
