import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public enum State {
    ON_THE_WAY {
        @Override
        public void changeState(Car car, ParkingCars parking, LocalDateTime time) {
            if (new Random().nextInt(101)+1 <= 3) {
                car.setStateObj(IN_THE_PARKING);
                Map<String, ArrayList<Report>> records = parking.getCarsRecords();
                ArrayList<Report> rec;
                if (records.get(car.getLicensePlate()) != null) rec = records.get(car.getLicensePlate());
                else rec = new ArrayList<>();
                rec.add(new Report(time));
                records.put(car.getLicensePlate(), rec);
                parking.setCarsRecords(records);
            }
        }
    },
    IN_THE_PARKING {
        @Override
        public void changeState(Car car, ParkingCars parking, LocalDateTime time) {
            if (new Random().nextInt( 101)+1 <= 3) {
                car.setStateObj(ON_THE_WAY);
                setTimeEnd(car, parking, time);

            }
        }
    };
    public void setTimeEnd(Car car, ParkingCars parking, LocalDateTime time) {
        Map<String, ArrayList<Report>> carRecords = parking.getCarsRecords();
        ArrayList<Report> recordCar = carRecords.get(car.getLicensePlate());
        Report rec = recordCar.get(recordCar.size() - 1);
        rec.setEndOfTime(time);
        recordCar.set(recordCar.size() - 1, rec);
        carRecords.put(car.getLicensePlate(), recordCar);
        parking.setCarsRecords(carRecords);
    }
    public abstract void changeState(Car car, ParkingCars parking, LocalDateTime time);
}
