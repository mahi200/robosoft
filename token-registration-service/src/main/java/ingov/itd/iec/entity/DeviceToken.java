package ingov.itd.iec.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "DEVICE_TOKEN")
public class DeviceToken {

    @Id
    String pan;
    String deviceToken;
    String deviceType;
    Date created;
    Date updated;
    
}
