package tokyo.ronin.tg.datebot.telegraph.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import tokyo.ronin.tg.datebot.telegraph.TelegraphMethod;
import tokyo.ronin.tg.datebot.telegraph.TelegraphStory;
import tokyo.ronin.tg.datebot.telegraph.response.AccountResponse;

import java.util.HashMap;
import java.util.Map;

@Component
public class TelegraphRestService {
    @Value("${telegraph.token}")
    private static String TELEGRAPH_TOKEN;

    RestTemplate restTemplate = new RestTemplate();
    private final String defaultUri = "https://api.telegra.ph/";
    private final String change = "https://telegra.ph/";

    public String createTelegraphPage(TelegraphStory telegraphStory) {
        String url = defaultUri + TelegraphMethod.CREATE_PAGE.getMethod();
        return sendRequest(telegraphStory, url);
    }

    public String editTelegraphStory(TelegraphStory telegraphStory, String storyName) {
        String url = defaultUri + TelegraphMethod.EDIT_PAGE.getMethod() + "/" + storyName.replaceAll(change, "");
        return sendRequest(telegraphStory, url);
    }

    public ResponseEntity<AccountResponse> createAccount(String shortName, String author_name) {
        Map<String, Object> uriVariables = new HashMap<>();
        uriVariables.put("short_name", shortName);
        uriVariables.put("author_name", author_name);

        String url = defaultUri + TelegraphMethod.CREATE_ACCOUNT.getMethod();
        return getRec(url, HttpMethod.GET, uriVariables, AccountResponse.class);
    }

    private <T> ResponseEntity<T> getRec(String url, HttpMethod httpMethod, Map<String, Object> requestBody, Class<T> response) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);
        return restTemplate.exchange(url, httpMethod, requestEntity, response);
    }


    private String sendRequest(TelegraphStory telegraphStory, String url) {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("access_token", TELEGRAPH_TOKEN);
        requestBody.put("title", telegraphStory.getTitle());
        requestBody.put("author_name", telegraphStory.getAuthorName());
        requestBody.put("content", telegraphStory.getStory());

        var responseEntity = getRec(url, HttpMethod.POST, requestBody, String.class);
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            String responseBody = responseEntity.getBody();
            JSONObject jsonResponse = new JSONObject(responseBody);

            if (jsonResponse.getBoolean("ok")) {
                return change + jsonResponse.getJSONObject("result").getString("path");
            } else {
                throw new RuntimeException("Error creating Telegraph page: " + jsonResponse.getString("error"));
            }
        } else {
            throw new RuntimeException("Error making the request: " + responseEntity.getStatusCode());
        }
    }
}