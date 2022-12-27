import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;
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

    public static void chooseAnAction() {
        print("\nCHOOSE ACTION: \n" +
                "[ 1 ] - Display parking reports\n" +
                "[ 2 ] - Display cars accounts\n");
        while (true) {
            print("Write the number: ");
            try {
                String str = new Scanner(System.in).nextLine();
                checkDataInput(str);
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void print(String s) {
        System.out.println(s);
    }

    private static void checkDataInput(String str) throws Exception {
        input = Integer.parseInt(str);
        if (input < 1 || input > 2)
            throw new Exception("NO SUCH ACTION");
    }

    public static void getAction(ParkingCars parking, Set<Car> cars) {
        switch (input) {
            case 1:
                parking.displayParkingOfCars(cars);
            case 2:
                parking.displayAccounts();
        }
    }
}
