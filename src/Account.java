public class Account {
    private String licensePlate;
    private double account;

    public Account(String licensePlate, double account) {
        this.licensePlate = licensePlate;
        this.account = account;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public double getAccount() {
        return account;
    }
}
