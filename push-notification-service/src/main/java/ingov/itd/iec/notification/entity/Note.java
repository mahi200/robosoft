package ingov.itd.iec.notification.entity;

import lombok.Data;

@Data
public class Note {

    private String subject;
    private String content;
    String notificationType;
    String pan;
    String deviceType;


}
