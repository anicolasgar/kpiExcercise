import app.domain.Sale;
import app.repositories.jpa.SalesRepository;
import app.services.SalesService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SalesTest {

    @Test
    public void testGetSecondsTimeShouldbeLowerThan60() {

        long timeInEpoch = new Date().getTime();
        Sale sale = new Sale(Double.valueOf(1000), timeInEpoch);

        assertTrue(sale.getSecondsTime()<60);
    }

    @Test
    public void testGetSecondsTimeShouldbeHigherThan60() {
        LocalDateTime currentTime = LocalDateTime.now(ZoneId.of("UTC")).minusHours(1);
        Sale sale = new Sale(Double.valueOf(1000), currentTime.toInstant(ZoneOffset.MAX).toEpochMilli());

        assertTrue(sale.getSecondsTime()>60);
    }

    @Test
    public void testSaleWithAmountHigherThanZeroShouldBeSale() {

        long timeInEpoch = new Date().getTime();
        Sale sale = new Sale(Double.valueOf(1000), timeInEpoch);

        assertTrue(sale.isSale());
    }

    @Test
    public void testSaleWithAmountLowerThanZeroShouldBeReturn() {

        long timeInEpoch = new Date().getTime();
        Sale sale = new Sale(Double.valueOf(-1000), timeInEpoch);

        assertFalse(sale.isSale());
    }

}
