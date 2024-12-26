package oose2324.group5.dispenseX.Repository;

import oose2324.group5.dispenseX.Model.Interval;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IntervalRepository extends JpaRepository<Interval, Integer> {
    Interval findIntervalByBinNumber(int binNumber);
}
