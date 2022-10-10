package ingov.itd.iec.notification.entity;

import lombok.Data;

import java.util.Date;


@Data
public class DeviceToken {


    String pan;
    String deviceToken;
    String deviceType;
    Date created;
    Date updated;




}
