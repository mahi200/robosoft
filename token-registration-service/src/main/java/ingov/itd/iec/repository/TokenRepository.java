package ingov.itd.iec.repository;

import ingov.itd.iec.entity.DeviceToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TokenRepository extends JpaRepository<DeviceToken, String> {
}
