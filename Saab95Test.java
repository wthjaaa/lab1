import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Saab95Test extends CarTest {

    @Override
    protected Car createCar() {
        return new Saab95();
    }

    @Override
    @Test
    void testSpeedFactor() {
        Saab95 saab = (Saab95) car;  // Cast to Saab95mod to access turbo functionality
        saab.setTurboOn();  // Turn on the turbo
        double expectedSpeedFactorTurboOn = car.getEnginePower() * 0.01 * 1.3;
        assertEquals(expectedSpeedFactorTurboOn, car.speedFactor(), 0.01, "The speed factor should be 1.3 times the base speed factor with turbo on");

        saab.setTurboOff();  // Turn off the turbo
        double expectedSpeedFactorTurboOff = car.getEnginePower() * 0.01 * 1;
        assertEquals(expectedSpeedFactorTurboOff, car.speedFactor(), 0.01, "The speed factor should be the base speed factor with turbo off");
    }
}
