package app.services;

import app.domain.Sale;
import app.domain.Statistic;
import app.repositories.jpa.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalesService {

    @Autowired
    SalesRepository salesRepository;

    public Sale saveSale(Sale sale) {
        Long timeInSeconds = sale.getSecondsTime();

        if (timeInSeconds > 60 || timeInSeconds < 0)
            return null;

        Statistic statistic = salesRepository.get(timeInSeconds);

        if (statistic == null || statistic.getTs() != sale.getTs())
            statistic = new Statistic(sale.getTs());

        if (sale.isSale()) {
            statistic.increaseTotalSalesCount();
            statistic.addTotalSalesAmount(sale.getAmount());
            statistic.checkAndAddNewMaxSale(sale.getAmount());
            statistic.checkAndAddNewMinSale(sale.getAmount());

        } else {
            statistic.increaseTotalReturnsCount();
            statistic.addTotalReturnsAmount(sale.getAmount());
            statistic.checkAndAddNewMaxReturn(sale.getAmount());
            statistic.checkAndAddNewMinReturn(sale.getAmount());
        }

        this.salesRepository.save(timeInSeconds, statistic);
        return sale;
    }


    public Statistic getStatisticsByLastMinute() {
        Statistic statisticByMinute = new Statistic(-1);
        for (long i = 0; i < 59; i++) {
            Statistic statisticBySecond = this.salesRepository.get(i);
            if (statisticBySecond == null)
                continue;

            statisticByMinute.increaseTotalSalesCount(statisticBySecond.getTotalSalesCount());
            statisticByMinute.addTotalSalesAmount(statisticBySecond.getTotalSalesAmount());
            statisticByMinute.checkAndAddNewMaxSale(statisticBySecond.getMaxSale());
            statisticByMinute.checkAndAddNewMinSale(statisticBySecond.getMinSale());

            statisticByMinute.increaseTotalReturnsCount(statisticBySecond.getTotalReturnsCount());
            statisticByMinute.addTotalReturnsAmount(statisticBySecond.getTotalReturnsAmount());
            statisticByMinute.checkAndAddNewMaxReturn(statisticBySecond.getMaxReturn());
            statisticByMinute.checkAndAddNewMinReturn(statisticBySecond.getMinReturn());
        }
        return statisticByMinute;
    }


}
