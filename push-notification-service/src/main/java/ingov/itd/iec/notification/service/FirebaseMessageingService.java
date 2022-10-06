package ingov.itd.iec.notification.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import ingov.itd.iec.notification.entity.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FirebaseMessageingService {

    @Autowired
    private FirebaseMessaging firebaseMessaging;


    public String sendNotification(Note note, String token) throws FirebaseMessagingException {

        Notification notification = Notification
                .builder()
                .setTitle(note.getSubject())
                .setBody(note.getContent())
                .build();

        Message message = Message
                .builder()
                .setToken(token)
                .setNotification(notification)
                //.putAllData(note.getData())
                .build();

        return firebaseMessaging.send(message);
    }



}
