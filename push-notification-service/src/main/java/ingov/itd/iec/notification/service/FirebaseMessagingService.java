package ingov.itd.iec.notification.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import ingov.itd.iec.notification.entity.Note;
import ingov.itd.iec.notification.exception.TokenNotFondException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class FirebaseMessagingService {

    @Value("${fcm.topic}")
    private String topic;

    @Autowired
    private FirebaseMessaging firebaseMessaging;

    @Autowired
    private TokenService tokenService;

    public String sendNotification(Note note) throws FirebaseMessagingException, TokenNotFondException {

        Notification notification = Notification
                .builder()
                .setTitle(note.getSubject())
                .setBody(note.getContent())
                .build();



        Message.Builder messageBuilder = Message
                .builder()
                .setNotification(notification);
        if (note.getNotificationType().toUpperCase().trim() == "MASS") {
            messageBuilder.setTopic(topic);
        }
        else {
            String token = tokenService.getToken(note.getPan());
            messageBuilder.setToken(token);
        }

        return firebaseMessaging.send(messageBuilder.build());
    }


}
