public class Main {
    public static void main(String[] args) {
        Simulation simulation = new Simulation();
        simulation.getSimulation();

        Simulation.chooseAnAction();
        Simulation.getAction(simulation.parkingCars, simulation.cars);

    }
}