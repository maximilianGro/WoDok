package eHealth.Repository;

import eHealth.entity.Practitioner;
import eHealth.entity.Queue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface QueueRepository extends JpaRepository<Queue, Long> {
    List<Queue> getQueuesByPractitioner(Practitioner practitioner);
}
