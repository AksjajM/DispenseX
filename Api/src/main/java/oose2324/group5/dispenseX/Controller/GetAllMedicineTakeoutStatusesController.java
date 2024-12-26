package oose2324.group5.dispenseX.Controller;

import oose2324.group5.dispenseX.Model.MedicineTakeoutStatus;
import oose2324.group5.dispenseX.Service.MedicineTakeoutStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GetAllMedicineTakeoutStatusesController {
    private final MedicineTakeoutStatusService service;

    @Autowired
    public GetAllMedicineTakeoutStatusesController(MedicineTakeoutStatusService service) {
        this.service = service;
    }

    @GetMapping("/get-medicine-takeout-statuses")
    public List<MedicineTakeoutStatus> getMedicineTakeoutStatuses() {
        return service.getAllMedicineTakeoutStatuses();
    }
}
