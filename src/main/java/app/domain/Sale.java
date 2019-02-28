package app.domain;

import java.time.*;
import java.util.Date;

public class Sale {
    Double amount;
    Long ts;


    public Double getAmount() {
        return amount;
    }

    public Long getTs() {
        return ts;
    }

    public Long getSecondsTime() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiry = Instant.ofEpochMilli(this.ts).atZone(ZoneId.systemDefault()).toLocalDateTime();

        Duration duration = Duration.between(expiry, now);

        return duration.getSeconds();
    }

    public boolean isSale() {
        return amount > 0; //amount cannot be ==0 because of business logic.
    }
}
