import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public class ParkingCars {
    StringBuilder SB = new StringBuilder();
    private static final String RETURN = "\n";
    private static final String LITTLE_LINE = ("*-------*-----------------------*----------*");
    private static final String FMT = ("| CAR № |    LICENSE_PLATE     |  ACCOUNT($) |");
    private static final String LINE = ("*-------------*---------------------*---------------------*");
    private static final String FORMAT = ("|  PARKING №  |     START TIME      |      END TIME       |");

    List<Car> parkingCars = new ArrayList<>();
    List<Account> accountList = new ArrayList<>();
    int sizeOfParking;
    private Map<String, ArrayList<Report>> carsRecords = new HashMap<>();

    public Map<String, ArrayList<Report>> getCarsRecords() {
        return carsRecords;
    }

    public void setCarsRecords(Map<String, ArrayList<Report>> carsRecords) {
        this.carsRecords = carsRecords;
    }

    public ParkingCars(int sizeOfParking) {
        this.sizeOfParking = sizeOfParking;
    }

    public void setTimeOfEnd(Set<Car> cars, LocalDateTime endOfTime) {
        ArrayList<Report> records;
        for (Car car : cars) {
            if (carsRecords.containsKey(car.getLicensePlate())) {
                records = carsRecords.get(car.getLicensePlate());
                records.get(records.size() - 1).setEndOfTime(endOfTime);
            }
        }
    }

    public void displayParkingOfCars(Set<Car> cars) {
        for (Car car : cars) {
            if (carsRecords.containsKey(car.getLicensePlate())) {
                ArrayList<Report> recordArrayList = carsRecords.get(car.getLicensePlate());
                System.out.printf("\n                    CAR: %s%n", car.getLicensePlate());
                SB.append(LINE);
                SB.append(FORMAT);
                SB.append(LINE);
                for (int i = 0; i < recordArrayList.size(); i++) {
                    System.out.printf("| %8s      |", i + 1);
                    recordArrayList.get(i).displayRecord();
                }
            }
        }
    }

    public double getCarsAccount(ArrayList<Report> reports) {
        double v = 0;
        long minute = 0;
        for (Report report : reports) {
            LocalDateTime begin = report.getBeginOfTime();
            LocalDateTime end = report.getEndOfTime();
            for (LocalDateTime i = begin; i.isBefore(end); i = i.plusMinutes(5)) {
                if (i.toLocalTime().isAfter(LocalTime.of(9, 0)) && i.toLocalTime().isBefore(LocalTime.of(21, 0))) {
                    minute += 5;
                    if (minute >= 30) {
                        v = minute * 0.02;
                    }
                }
            }
        }
        return v;
    }

    public void takeCarsAc(Set<Car> cars) {
        ArrayList<Report> reports;
        for (Car car : cars) {
            if (carsRecords.containsKey(car.getLicensePlate())) {
                reports = carsRecords.get(car.getLicensePlate());
                accountList.add(new Account(car.getLicensePlate(), getCarsAccount(reports)));
            }
        }
    }

    public void displayAccounts() {
        SB.append(RETURN).append(LITTLE_LINE).append(RETURN);
        SB.append(FMT).append(RETURN);
        SB.append(LITTLE_LINE);
        for (int i = 0; i < accountList.size(); i++) {
            System.out.printf("| %4s  | %-12s |  %.2f  |%n", i + 1, accountList.get(i).getLicensePlate(), accountList.get(i).getAccount());
            SB.append(LITTLE_LINE);
        }
    }

    public double getAverageAccountPerMonth() {
        double dailyAccount = 0;
        double averageAccount = 0;
        for (int i = 0; i < accountList.size(); i++) {
            dailyAccount += accountList.get(i).getAccount();
        }
        return averageAccount = dailyAccount / 30;
    }
}
