package io.waggeh.waggeh.proxy;

import io.waggeh.waggeh.model.requests.ZoomMeetingRequest;
import io.waggeh.waggeh.model.responses.ApiResponseWrapper;
import io.waggeh.waggeh.model.zoom.MeetingInvitee;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ZoomMeetingProxy {

    @Value("${zoom.api.baseurl}")
    private String zoomApiBaseUrl;

    @Value("${zoom.clientId}")
    private String zoomClientId;

    @Value("${zoom.clientSecret}")
    private String zoomClientSecret;

    @Value("${zoom.accountId}")
    private String zoomAccountID;

    public ApiResponseWrapper<Map<String,String>> createMeeting(ZoomMeetingRequest meetingRequest,String accessToken){
        try {
            RestTemplate restTemplate = new RestTemplate();
            Map<String,Object> settings = new HashMap<>();

            settings.put("meeting_authentication",true);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(accessToken);
            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("topic", meetingRequest.getTopic());
            requestBody.put("type", 2); // Scheduled Meeting
            requestBody.put("settings",settings);

            // Add other necessary fields to the request body

            HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

            // Construct Zoom API endpoint for creating a meeting
            String createMeetingEndpoint = zoomApiBaseUrl + "/users/me/meetings";

            // Send the POST request to Zoom API
            ResponseEntity<Map> responseEntity = restTemplate.exchange(
                    createMeetingEndpoint,
                    HttpMethod.POST,
                    requestEntity,
                    Map.class
            );

            Map<String, Object> responseBody = responseEntity.getBody();
            String clientJoinUrl = (String) responseBody.get("join_url");
            String consultantJoinUrl = (String) responseBody.get("start_url");
            Map<String,String> URLS = new HashMap<>();
            URLS.put("clientURL",clientJoinUrl);
            URLS.put("consultantURL",consultantJoinUrl);


            return new ApiResponseWrapper(true,"Zoom meeting created successfully",URLS);
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResponseWrapper(false,"Can't create zoom meeting",e.getMessage());
        }
    }

    public String auth() {
        RestTemplate restTemplate = new RestTemplate();
        String token = "";

        // Construct Zoom API endpoint for authentication
        String authEndpoint = "https://zoom.us/oauth/token";

        // Set up request headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBasicAuth(zoomClientId, zoomClientSecret);

        // Set up the request body for authentication
        String requestBody = "grant_type=account_credentials&account_id=" + zoomAccountID;
        HttpEntity<String> httpEntity = new HttpEntity<>(requestBody, headers);

        // Send the POST request to Zoom API using RestTemplate
        ResponseEntity<Map> responseEntity = restTemplate.exchange(authEndpoint, HttpMethod.POST, httpEntity, Map.class);

        Map<String, Object> responseBody = responseEntity.getBody();
        if (responseBody != null) {
            token = (String) responseBody.get("access_token");
        }

        return token;
    }
}
