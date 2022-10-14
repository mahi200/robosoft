package ingov.itd.iec.notification.controller;
// Importing required classes

import com.google.firebase.messaging.FirebaseMessagingException;
import ingov.itd.iec.notification.entity.Note;
import ingov.itd.iec.notification.exception.TokenNotFondException;
import ingov.itd.iec.notification.service.FirebaseMessageingService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PushNotificationController {

    @Autowired
    FirebaseMessageingService firebaseMessageingService;

    @PostMapping("/send-notification")
    public ResponseEntity<String> sendNotification(@RequestBody Note note) throws FirebaseMessagingException {
        try {
            return new ResponseEntity<>(firebaseMessageingService.sendNotification(note), HttpStatus.OK);
        } catch (TokenNotFondException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

}


