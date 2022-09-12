package push.notification;
// Importing required classes

import com.google.firebase.messaging.FirebaseMessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

// Annotation
@RestController

// Class
public class DemoController {

    // Autowiring Kafka Template
    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    FirebaseMessageingService firebaseMessageingService;
    private static final String TOPIC = "NewTopic";

    // Publish messages using the GetMapping
    @GetMapping("/publish/{message}")
    public String publishMessage(@PathVariable("message") final String message) {

        // Sending the message
        kafkaTemplate.send(TOPIC, message);

        return "Published Successfully";
    }
    @RequestMapping("/send-notification")
    @ResponseBody
    public String sendNotification(@RequestBody Note note,
                                   @RequestParam String token) throws FirebaseMessagingException {
        return firebaseMessageingService.sendNotification(note, token);
    }
}


