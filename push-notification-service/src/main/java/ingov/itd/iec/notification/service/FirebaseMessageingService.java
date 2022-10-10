package ingov.itd.iec.notification.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import ingov.itd.iec.notification.entity.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FirebaseMessageingService {

    @Autowired
    private FirebaseMessaging firebaseMessaging;
    @Autowired
    private TokenService tokenService;

    public String sendNotification(Note note) throws FirebaseMessagingException {

        Notification notification = Notification
                .builder()
                .setTitle(note.getSubject())
                .setBody(note.getContent())
                .build();

        String token = tokenService.getToken(note.getPan());
        Message message = Message
                .builder()
                .setToken(token)
                .setNotification(notification)
                //.putAllData(note.getData())
                .build();

        return firebaseMessaging.send(message);
    }


}
