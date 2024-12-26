package oose2324.group5.dispenseX.Controller;

import oose2324.group5.dispenseX.Model.Interval;
import oose2324.group5.dispenseX.Service.IntervalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@RestController
public class CheckMedicineRemovalController {
    private final IntervalService service;

    @Autowired
    public CheckMedicineRemovalController(IntervalService service) {
        this.service = service;
    }

    @GetMapping(path = "/check-medicine-removal")
    public int getIntervalByTimeStamp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        LocalDate currentDate = LocalDate.now();
        DayOfWeek dayOfWeek = currentDate.getDayOfWeek();

        Interval interval = service.getIntervalByBinNumber(dayOfWeek.getValue());

        if (interval == null) {
            return 0;
        }

        String timeOfDisposalFormatted = formatter.format(interval.getTimeOfDisposal());
        String nowFormatted = formatter.format(LocalTime.now());

        if (timeOfDisposalFormatted.equals(nowFormatted)) {
            return interval.getBinNumber();
        }

        return 0;
    }
}
