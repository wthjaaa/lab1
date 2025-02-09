
import java.awt.*;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

public class CarTransport extends Truck {

    private boolean rampDown; // True when ramp is down
    private Deque<Car> loadedCars; //Stack
    private final int max_cars = 8;
    private static final java.util.List<CarTransport> allCarTransports = new ArrayList<>();

    public CarTransport(Color color, double enginePower) {
        super(2,enginePower, color, "Car Transport");
        this.rampDown = false;
        this.loadedCars = new LinkedList<>();
        synchronized (allCarTransports) {
            allCarTransports.add(this);
        }
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

        if (loadedCars.contains(car)){
            throw new IllegalStateException("Bilen är redan lastad!");
        }

        if (loadedCars.size() >= max_cars) {
            throw new IllegalStateException("Fartyget är redan fullt");
        }
        if (Math.abs(car.getX() - getX()) > 2 || Math.abs(car.getY() - getY()) > 2) {
            throw new IllegalArgumentException("Bilen är för långt bort för att lastas");
        }

        synchronized (allCarTransports) {
            for (CarTransport transport : allCarTransports) {
                if (transport != this && transport.loadedCars.contains(car)) {
                    throw new IllegalStateException("Bilen är redan lastad i en annan Transport bil");
                }
            }
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

    public boolean isCarLoaded(Car car) {
        return loadedCars.contains(car);
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