package ingov.itd.iec.notification.controller;
// Importing required classes

import com.google.firebase.messaging.FirebaseMessagingException;
import ingov.itd.iec.notification.entity.Note;
import ingov.itd.iec.notification.service.FirebaseMessageingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PushNotificationController {


    @Autowired
    FirebaseMessageingService firebaseMessageingService;

    @PostMapping("/send-notification")
    @ResponseBody
    public String sendNotification(@RequestBody Note note) throws FirebaseMessagingException {
        return firebaseMessageingService.sendNotification(note);
    }

}


