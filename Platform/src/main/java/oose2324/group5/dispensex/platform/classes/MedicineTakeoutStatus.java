package oose2324.group5.dispensex.platform.classes;

import java.time.LocalTime;

public class MedicineTakeoutStatus {
    private Integer id;
    private String dayOfWeek;
    private String medicineName;
    private Integer numberOfMedicine;
    private LocalTime timeOfTakeout;
    private LocalTime timeOfDisposal;

    public MedicineTakeoutStatus(Integer id, String timeOfTakeout, String timeOfDisposal, String dayOfWeek, String medicineName, Integer numberOfMedicine) {
        this.id = id;
        this.dayOfWeek = dayOfWeek;
        this.medicineName = medicineName;
        this.numberOfMedicine = numberOfMedicine;
        this.timeOfTakeout = LocalTime.parse(timeOfTakeout);
        this.timeOfDisposal = LocalTime.parse(timeOfDisposal);
    }

    public Integer getId() {
        return id;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public Integer getNumberOfMedicine() {
        return numberOfMedicine;
    }

    public LocalTime getTimeOfTakeout() {
        return timeOfTakeout;
    }

    public LocalTime getTimeOfDisposal() {
        return timeOfDisposal;
    }
}
