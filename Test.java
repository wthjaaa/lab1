import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;



abstract class CarTest {
    protected Car car;

    @BeforeEach
    void setUp() {
        car = createCar();
    }

    protected abstract Car createCar();

    @Test
    void testInitialSpeed() {
        assertEquals(0, car.getCurrentSpeed(), "Car speed should initially be 0");
    }

    @Test
    void testStartEngine() {
        car.startEngine();
        assertTrue(car.getCurrentSpeed() > 0, "The car's speed should be greater than 0 after starting the engine");
    }

    @Test
    void testStopEngine() {
        car.startEngine();
        car.stopEngine();
        assertEquals(0, car.getCurrentSpeed(), "Car speed should be 0 after stopping the engine");
    }

    @Test
    void testMove() {
        car.startEngine();
        car.gas(0.5);
        double initialY = car.getY();
        car.move();
        assertTrue(car.getY() > initialY, "The car should have moved in y-direction");
    }

    @Test
    void testMoveLeft() {
        int initialDirection = car.getDirection();
        car.turnLeft();
        assertEquals((initialDirection - 1 + 4) % 4, car.getDirection(), "The direction should be one step counter clockwise");
    }

    @Test
    void testMoveRight() {
        int initialDirection = car.getDirection();
        car.turnRight();
        assertEquals((initialDirection + 1) % 4, car.getDirection(), "The direction should be one step clockwise");
    }

    @Test
    void testGas() {
        car.startEngine();
        double initialSpeed = car.getCurrentSpeed();
        car.gas(0.5);
        assertTrue(car.getCurrentSpeed() > initialSpeed, "The speed should increase after gas");
    }

    @Test
    void testBrake() {
        car.startEngine();
        car.gas(0.5);
        double initialSpeed = car.getCurrentSpeed();
        car.brake(0.5);
        assertTrue(car.getCurrentSpeed() < initialSpeed, "The speed should decrease after brake");
    }

    @Test
    void testSpeedFactor() {
        double expectedSpeedFactor = car.getEnginePower() * 0.01 * car.speedFactor();
        assertEquals(expectedSpeedFactor, car.speedFactor(), 0.01, "The speed should be calculated correctly");
    }
}
