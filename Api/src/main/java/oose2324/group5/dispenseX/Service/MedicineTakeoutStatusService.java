package oose2324.group5.dispenseX.Service;

import oose2324.group5.dispenseX.Model.MedicineTakeoutStatus;
import oose2324.group5.dispenseX.Repository.MedicineTakeoutStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineTakeoutStatusService {
    private final MedicineTakeoutStatusRepository repository;

    @Autowired
    public MedicineTakeoutStatusService(MedicineTakeoutStatusRepository repository) {
        this.repository = repository;
    }

    public MedicineTakeoutStatus addStatus(MedicineTakeoutStatus newStatus) {
        // Save the new status to the database
        return repository.saveAndFlush(newStatus);
    }

    public List<MedicineTakeoutStatus> getAllMedicineTakeoutStatuses() {
        return repository.findAll();
    }
}
