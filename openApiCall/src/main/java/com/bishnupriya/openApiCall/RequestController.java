package com.bishnupriya.openApiCall;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class RequestController {

    private final RestTemplate restTemplate = new RestTemplate();

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
    public ResponseEntity<String> handleRequest(@RequestBody(required = false) RequestDataService requestData) {
        String apiUrl = "";
        HttpMethod method = HttpMethod.GET;
        Object requestPayload = null;

        if (requestData != null) {
            // Determine the API to call based on requestData
            switch (requestData.getCondition()) {
                case "get":
                    apiUrl = "https://reqres.in/api/users?page=2";
                    method = HttpMethod.GET;
                    break;
                case "post":
                    apiUrl = "https://reqres.in/api/users";
                    method = HttpMethod.POST;
                    requestPayload = new PostRequestPayload("John Doe", "Developer");
                    break;
                case "put":
                    apiUrl = "https://reqres.in/api/users/2";
                    method = HttpMethod.PUT;
                    requestPayload = new PutRequestPayload("Jane", "Doe", "jane.doe@example.com");
                    break;
                case "delete":
                    apiUrl = "https://reqres.in/api/users/2";
                    method = HttpMethod.DELETE;
                    break;
                default:
                    return new ResponseEntity<>("Invalid condition", HttpStatus.BAD_REQUEST);
            }
        } else {
            // Default GET request
            apiUrl = "https://reqres.in/api/users?page=2";
            method = HttpMethod.GET;
        }

        try {
            HttpEntity<Object> entity = new HttpEntity<>(requestPayload, new HttpHeaders());
            ResponseEntity<String> response = restTemplate.exchange(apiUrl, method, entity, String.class);
            return new ResponseEntity<>(response.getBody(), response.getStatusCode());
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
