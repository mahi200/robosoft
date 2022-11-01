package ingov.itd.iec.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "DEVICE_TOKEN")
@Data
public class DeviceToken {

    public DeviceToken() {

    }

    @Id
    String pan;
    String deviceToken;
    String deviceType;
    Date created;
    Date updated;
    Boolean topicSubscription;


    public DeviceToken(String pan, String deviceToken, String deviceType) {
        this.pan = pan;
        this.deviceToken = deviceToken;
        this.deviceType = deviceType;
        this.created = new Date();
        this.updated = new Date();
    }


}
