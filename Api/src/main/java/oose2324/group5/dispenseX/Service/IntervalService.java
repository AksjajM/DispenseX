package oose2324.group5.dispenseX.Service;

import oose2324.group5.dispenseX.Model.Interval;
import oose2324.group5.dispenseX.Repository.IntervalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IntervalService {
    private final IntervalRepository repository;

    @Autowired
    public IntervalService(IntervalRepository repository) {
        this.repository = repository;
    }

    public Interval createInterval(Interval interval) {
        Interval existingInterval = repository.findIntervalByBinNumber(interval.getBinNumber());

        if (existingInterval != null) {
            repository.delete(existingInterval);
        }

        return repository.saveAndFlush(interval);
    }

    public Interval getIntervalByBinNumber(Integer binNumber){
        return repository.findIntervalByBinNumber(binNumber);
    }
}
