package ingov.itd.iec.controller;

import java.util.*;

import ingov.itd.iec.entity.DeviceToken;
import ingov.itd.iec.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")

public class TokenController {
    @Autowired
    TokenRepository tokenRepository;

    @GetMapping("/deviceToken")
    public ResponseEntity<List<DeviceToken>> getAllDeviceToken() {
        try {
            List<DeviceToken> DeviceToken = new ArrayList<DeviceToken>();

            tokenRepository.findAll().forEach(DeviceToken::add);

            if (DeviceToken.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(DeviceToken, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/deviceToken/{pan}")
    public ResponseEntity<DeviceToken> geTokenById(@PathVariable("pan") String pan) {
        Optional<DeviceToken> deviceToken = tokenRepository.findById(pan.toUpperCase());

        if (deviceToken.isPresent()) {
            return new ResponseEntity<>(deviceToken.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/deviceToken")
    public ResponseEntity<DeviceToken> createDeviceToken(@RequestBody DeviceToken deviceToken) {
        try {
            if (deviceToken.getPan() != null) {
                DeviceToken _DeviceToken = tokenRepository
                        .save(new DeviceToken(deviceToken.getPan().toUpperCase(), deviceToken.getDeviceToken(), deviceToken.getDeviceType()));
                return new ResponseEntity<>(_DeviceToken, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/deviceToken/{pan}")
    public ResponseEntity<Object> updateDeviceToken(@PathVariable("pan") String pan, @RequestBody DeviceToken deviceToken) {
        Optional<DeviceToken> deviceTokenOptional = tokenRepository.findById(pan.toUpperCase());

        if (deviceTokenOptional.isPresent()) {
            DeviceToken _deviceToken = deviceTokenOptional.get();
            _deviceToken.setDeviceToken(deviceToken.getDeviceToken());
            _deviceToken.setDeviceType(deviceToken.getDeviceType());
            _deviceToken.setUpdated(new Date());
            return new ResponseEntity<>(tokenRepository.save(_deviceToken), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("token not found for the pan :" + pan, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deviceToken/{id}")
    public ResponseEntity<String> deleteDeviceToken(@PathVariable("id") String pan) {
        try {
            tokenRepository.deleteById(pan);
            return new ResponseEntity<>(" token deleted successful for pan:" + pan, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("no device token found for pan : " + pan, HttpStatus.NOT_FOUND);
        }
    }


}
