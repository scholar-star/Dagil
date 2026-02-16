package busan_dining.dagil.services;

import busan_dining.dagil.entities.Landmarks;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Service
public class AIRecommandService {
    @Value("${upstage.apikey}")
    private String apiKey;

}
