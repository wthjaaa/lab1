import java.util.ArrayList;
import java.util.List;

public class Workshop<T extends Car> {
    private final int maxCapacity;
    private final List<T> cars;

    public Workshop(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        this.cars = new ArrayList<>();
    }
    public void loadCar(T car) {
        if (cars.size() >= maxCapacity) { // Call size() on the list, not on the car
            throw new IllegalStateException("Workshop is full");
        }
        cars.add(car);
    }
    public T unloadCar() {
        if (cars.isEmpty()) {
            throw new IllegalStateException("No cars in the workshop");
        }
        return cars.remove(cars.size()-1);

    }

    public int getNumberOfCars() {
        return cars.size();
    }
}
