package ingov.itd.iec.notification.service;

import ingov.itd.iec.notification.entity.DeviceToken;
import ingov.itd.iec.notification.exception.TokenNotFondException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Service
public class TokenService {

    @Value("${token.service.url:localhost:9090/api/deviceToken}")
    private String tokenServiceBaseUrl;

    @Autowired
    private RestTemplate restTemplate;


    public String getToken(String pan) throws TokenNotFondException {
        final String baseUrl = tokenServiceBaseUrl+"/"+pan;
        URI uri = null;
        try {
            uri = new URI(baseUrl);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        ResponseEntity<DeviceToken> token = null;
        try {
            token = restTemplate.getForEntity(uri, DeviceToken.class);
        }
        catch(HttpClientErrorException.NotFound e){
            throw new TokenNotFondException("Token not exist for the pan : "+pan);
        }
        return token.getBody().getDeviceToken();
    }
}
