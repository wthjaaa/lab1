import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;



abstract class TruckTest {
    protected Truck truck;

    @BeforeEach
    void setUp() {
        truck = createTruck();
    }

    protected abstract Truck createTruck();

    @Test
    void testSpeedFactor() {
        double expectedSpeedFactor = truck.getEnginePower() * 0.01 * truck.speedFactor();
        assertEquals(expectedSpeedFactor, truck.speedFactor(), 0.01, "The speed should be calculated correctly");
    }
}
