package oose2324.group5.dispenseX.Controller;

import oose2324.group5.dispenseX.Model.Interval;
import oose2324.group5.dispenseX.Model.MedicineTakeoutStatus;
import oose2324.group5.dispenseX.Service.IntervalService;
import oose2324.group5.dispenseX.Service.MedicineTakeoutStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

@RestController
public class MedicineDisposedController {
    private final MedicineTakeoutStatusService medicineTakeoutStatusService;
    private final IntervalService intervalService;

    @Autowired
    public MedicineDisposedController(MedicineTakeoutStatusService medicineTakeoutStatusService, IntervalService intervalService) {
        this.medicineTakeoutStatusService = medicineTakeoutStatusService;
        this.intervalService = intervalService;
    }

    @PostMapping("/medicine-disposed")
    public ResponseEntity<MedicineTakeoutStatus> addStatus() {
        MedicineTakeoutStatus medicineTakeoutStatus = new MedicineTakeoutStatus();

        LocalDate currentDate = LocalDate.now();
        DayOfWeek dayOfWeek = currentDate.getDayOfWeek();

        Interval interval = intervalService.getIntervalByBinNumber(dayOfWeek.getValue());
        String dayOfWeekString;

        switch (interval.getBinNumber()) {
            case 1:
                dayOfWeekString = "Maandag";
                break;
            case 2:
                dayOfWeekString = "Dinsdag";
                break;
            case 3:
                dayOfWeekString = "Woensdag";
                break;
            case 4:
                dayOfWeekString = "Donderdag";
                break;
            default:
                dayOfWeekString = "Onbekend";
        }

        medicineTakeoutStatus.setOriginalDayOfWeek(dayOfWeekString);
        medicineTakeoutStatus.setOriginalTimeOfDisposal(interval.getTimeOfDisposal());
        medicineTakeoutStatus.setOriginalMedicineName(interval.getMedicineName());
        medicineTakeoutStatus.setOriginalNumberOfMedicines(interval.getNumberOfMedicines());

        medicineTakeoutStatus.setTime_of_takeout(LocalTime.now());
        MedicineTakeoutStatus createdStatus = medicineTakeoutStatusService.addStatus(medicineTakeoutStatus);

        // Return the created status with a 201 Created status code
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStatus);
    }
}
