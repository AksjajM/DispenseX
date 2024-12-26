package oose2324.group5.dispenseX.Model;

import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
@Table(name = "medicine_takeout_status")
public class MedicineTakeoutStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "original_day_of_week")
    private String originalDayOfWeek;

    @Column(name = "original_time_of_disposal")
    private LocalTime originalTimeOfDisposal;

    @Column(name = "time_of_takeout")
    private LocalTime timeOfTakeout;

    @Column(name = "original_medicine_name")
    private String originalMedicineName;

    @Column(name = "original_number_of_medicines")
    private int originalNumberOfMedicines;

    public MedicineTakeoutStatus() {

    }

    public Integer getId() {
        return id;
    }

    public String getOriginalDayOfWeek() {
        return originalDayOfWeek;
    }

    public void setOriginalDayOfWeek(String originalDayOfWeek) {
        this.originalDayOfWeek = originalDayOfWeek;
    }

    public LocalTime getOriginalTimeOfDisposal() {
        return originalTimeOfDisposal;
    }

    public void setOriginalTimeOfDisposal(LocalTime originalTimeOfDisposal) {
        this.originalTimeOfDisposal = originalTimeOfDisposal;
    }

    public LocalTime getTimeOf_takeout() {
        return timeOfTakeout;
    }

    public void setTime_of_takeout(LocalTime timeOfTakeout) {
        this.timeOfTakeout = timeOfTakeout;
    }

    public String getOriginalMedicineName() {
        return originalMedicineName;
    }

    public void setOriginalMedicineName(String originalMedicineName) {
        this.originalMedicineName = originalMedicineName;
    }

    public int getOriginalNumberOfMedicines() {
        return originalNumberOfMedicines;
    }

    public void setOriginalNumberOfMedicines(int originalNumberOfMedicines) {
        this.originalNumberOfMedicines = originalNumberOfMedicines;
    }
}
