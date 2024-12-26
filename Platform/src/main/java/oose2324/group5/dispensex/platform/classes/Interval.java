package oose2324.group5.dispensex.platform.classes;

import java.time.LocalTime;

public class Interval {
    private Integer binNumber;
    private LocalTime timeOfDisposal;
    private String nameOfMedicine;
    private Integer numberOfMedicines;

    public Interval(Integer binNumber, LocalTime timeOfDisposal, String nameOfMedicine, Integer numberOfMedicines) {
        this.binNumber = binNumber;
        this.timeOfDisposal = timeOfDisposal;
        this.nameOfMedicine = nameOfMedicine;
        this.numberOfMedicines = numberOfMedicines;
    }

    public Integer getBinNumber() {
        return binNumber;
    }

    public LocalTime getTimeOfDisposal() {
        return timeOfDisposal;
    }

    public String getNameOfMedicine() {
        return nameOfMedicine;
    }

    public Integer getNumberOfMedicines() {
        return numberOfMedicines;
    }
}
