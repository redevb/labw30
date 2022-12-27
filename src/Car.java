import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Car implements Comparable<Car> {
    private String licensePlate;
    private State stateObj;

    public Car(String licensePlate, State stateObj) {
        this.licensePlate = licensePlate;
        this.stateObj = stateObj;
    }


    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public void setDriveState() {
        stateObj = State.ON_THE_WAY;
    }

    public State getStateObj() {
        return stateObj;
    }

    public void setStateObj(State stateObj) {
        this.stateObj = stateObj;
    }

    public static String makeLicensePlate() {
        int rndmRegion = new Random().nextInt(9) + 0;
        List<String> region = List.of("01KG", "02KG", "03KG", "04KG", "05KG", "06KG", "07KG", "08KG", "09KG");
        int number = new Random().nextInt(100) + 1000;
        char firstLetter = (char) (new Random().nextInt(26) + 'A');
        char secondLetter = (char) (new Random().nextInt(26) + 'A');
        char thirdLetter = (char) (new Random().nextInt(26) + 'A');
        String letters = String.format("%s%s%s", firstLetter, secondLetter, thirdLetter);
        return String.format("%s %s %s", region.get(rndmRegion), number, letters);
    }

    public void changeState(ParkingCars parking, LocalDateTime time) {
        stateObj.changeState(this, parking, time);
    }

    @Override
    public String toString() {
        return String.format("Car: %s | State: %s", licensePlate, stateObj);
    }

    @Override
    public int compareTo(Car o) {
        return getLicensePlate().compareTo(o.getLicensePlate());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(licensePlate, car.licensePlate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(licensePlate);
    }
}
