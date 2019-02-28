package app.domain;

public class Statistic {
    private long ts;

    private long totalSalesCount;
    private double totalSalesAmount;
    private double maxSale;
    private double minSale;
    private long totalReturnsCount;
    private double totalReturnsAmount;
    private double maxReturn;
    private double minReturn;

    public Statistic(long ts) {
        this.ts = ts;
    }

    public long getTs() {
        return ts;
    }

    public long getTotalSalesCount() {
        return totalSalesCount;
    }

    public double getTotalSalesAmount() {
        return totalSalesAmount;
    }

    public double getMaxSale() {
        return maxSale;
    }

    public double getMinSale() {
        return minSale;
    }

    public long getTotalReturnsCount() {
        return totalReturnsCount;
    }

    public double getTotalReturnsAmount() {
        return totalReturnsAmount;
    }

    public double getMaxReturn() {
        return maxReturn;
    }

    public double getMinReturn() {
        return minReturn;
    }

    //Sales
    public void increaseTotalSalesCount() {
        this.totalSalesCount++;
    }

    public void increaseTotalSalesCount(long count) {
        this.totalSalesCount += count;
    }

    public void addTotalSalesAmount(double newAmount) {
        this.totalSalesAmount += newAmount;
    }

    public void checkAndAddNewMaxSale(double amount) {
        if (this.maxSale < amount)
            this.maxSale = amount;
    }

    public void checkAndAddNewMinSale(double amount) {
        if ((amount != 0 && this.minSale > amount) || this.minSale == 0)
            this.minSale = amount;
    }

    //Returns
    public void increaseTotalReturnsCount() {
        this.totalReturnsCount++;
    }

    public void increaseTotalReturnsCount(long count) {
        this.totalReturnsCount += count;
    }

    public void addTotalReturnsAmount(double newAmount) {
        this.totalReturnsAmount += newAmount;
    }

    public void checkAndAddNewMaxReturn(double amount) {
        if (this.maxReturn > amount)
            this.maxReturn = amount;
    }

    public void checkAndAddNewMinReturn(double amount) {
        if ((amount != 0 && this.minReturn < amount) || this.minReturn == 0)
            this.minReturn = amount;
    }

}
