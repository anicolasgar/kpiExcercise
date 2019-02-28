package app.repositories.jpa;

import app.domain.Statistic;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class SalesRepository {
    private ConcurrentHashMap<Long, Statistic> statistics = new ConcurrentHashMap<>();

    public Statistic save(Long second, Statistic statistic) {
        return this.statistics.put(second, statistic);
    }


    public Statistic get(Long second) {
        return this.statistics.get(second);
    }
}
