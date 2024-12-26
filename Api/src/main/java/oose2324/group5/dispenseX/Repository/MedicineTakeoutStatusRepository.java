package oose2324.group5.dispenseX.Repository;

import oose2324.group5.dispenseX.Model.MedicineTakeoutStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicineTakeoutStatusRepository extends JpaRepository<MedicineTakeoutStatus, Integer> {
}
