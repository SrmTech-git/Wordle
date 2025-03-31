package org.techelevator.Services;

import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;

public class APIWordService implements WordService {



    private static final String API_BASE_URL = "https://api.dictionaryapi.dev/api/v2/entries/en/";
    private final RestClient restClient = RestClient.create(API_BASE_URL);

    public APIWordService() {
    }


//*************
//   Methods
//*************


    @Override
    public boolean getWordValidity(String word) {

        try {

            var response =  restClient.get()
                    .uri(word)
                    .retrieve();
            if (response.toBodilessEntity().getStatusCode().value() == 200) {
                return true;
            }

        } catch (RestClientResponseException | ResourceAccessException e) {
            //BasicLogger.log(e.getMessage());
        }
        return false;
    }
}
