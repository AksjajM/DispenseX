package oose2324.group5.dispenseX.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import java.time.LocalTime;

@Entity
@Table(name = "medicine_interval")
public class Interval {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "bin_number", unique = true)
    @Min(1)
    @Max(4)
    private int binNumber;

    @Column(name = "time_of_disposal")
    private LocalTime timeOfDisposal;

    @Column(name = "medicine_name")
    private String medicineName;

    @Column(name = "number_of_medicines")
    private int numberOfMedicines;

    public Interval(int binNumber, LocalTime timeOfDisposal, String medicineName, int numberOfMedicines) {
        this.binNumber = binNumber;
        this.timeOfDisposal = timeOfDisposal;
        this.medicineName = medicineName;
        this.numberOfMedicines = numberOfMedicines;
    }

    public Interval() {

    }

    public Integer getId() {
        return id;
    }

    public int getBinNumber() {
        return binNumber;
    }

    public void setBinNumber(int binNumber) {
        this.binNumber = binNumber;
    }

    public LocalTime getTimeOfDisposal() {
        return timeOfDisposal;
    }

    public void setTimeOfDisposal(LocalTime timeOfDisposal) {
        this.timeOfDisposal = timeOfDisposal;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public int getNumberOfMedicines() {
        return numberOfMedicines;
    }

    public void setNumberOfMedicines(int numberOfMedicines) {
        this.numberOfMedicines = numberOfMedicines;
    }
}
