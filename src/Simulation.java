import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Simulation {
    private static String string;
    private static int input;
    Set<Car> cars = new TreeSet<>();
    ParkingCars parkingCars = new ParkingCars(20);
    List<Account> accountList = parkingCars.accountList;

    public void getSimulation() {
        for (int i = 0; i < 200; i++) {
            cars.add(new Car(Car.makeLicensePlate(), State.ON_THE_WAY));
        }
        LocalDateTime time = LocalDateTime.now();
        for (LocalDateTime i = time; i.isBefore(time.plusDays(30)); i = i.plusMinutes(5)) {
            for (Car car : cars) {
                car.changeState(parkingCars, i);
            }
            if (i.plusMinutes(5).equals(time.plusDays(30))) {
                cars.forEach(Car::setDriveState);
                parkingCars.setTimeOfEnd(cars, i);
            }
        }
        parkingCars.takeCarsAc(cars);
    }
}
