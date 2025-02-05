
import java.awt.*;
import java.util.Deque;
import java.util.LinkedList;

public class CarTransport extends Car {

    private boolean rampDown; // True when ramp is down
    private Deque<Car> loadedCars; //Stack
    private final int max_cars = 8;

    public CarTransport(Color color, double enginePower) {
        super(2,enginePower,color, "Car Transport");
        this.rampDown = false;
        this.loadedCars = new LinkedList<>();
    }



    public boolean isRampDown() {
        return rampDown;
    }

    public void lowerRamp() {
        if (getCurrentSpeed() == 0) {
            rampDown = true;
        }
        else {
            throw new IllegalStateException("Fartyget är i rörelse. Ej tillåtet att sänka rampen!");
        }
    }

    public void raiseRamp() {
        rampDown = false;
    }

    public void loadCar(Car car) {
        if (!rampDown) {
            throw new IllegalStateException("Rampen måste vara nere för att lasta bilar!");
        }
        if (car instanceof CarTransport) {
            throw new IllegalArgumentException("Kan inte lasta en CarTransport bil");
        }
        if (loadedCars.size() >= max_cars) {
            throw new IllegalStateException("Fartyget är redan fullt");
        }
        if (Math.abs(car.getX() - getX()) > 2 || Math.abs(car.getY() - getY()) > 2) {
            throw new IllegalArgumentException("Bilen är för långt bort för att lastas");
        }

        loadedCars.push(car);
        car.setPosition(getX(), getY());
    }

    public Car unloadCar() {
        if(!rampDown) {
            throw new IllegalStateException("Ramp måste vara nere för att lasta av bilar");
    }
        if (loadedCars.isEmpty()) {
            throw new IllegalStateException("Finns inga bilar som ska lastas av");
        }

        Car car = loadedCars.pop();
        car.setPosition(getX() + 2, getY());
        return car;

    }

    @Override
    public void move() {
        if (!rampDown) {
            super.move();
            for (Car car : loadedCars) {
                car.setPosition(getX(), getY());
            }
        } else {
            throw new IllegalStateException("Fartyget får ej köras när rampen är nere");
        }
    }

    @Override
    public double speedFactor() {
        return getEnginePower()*0.05;
    }

}