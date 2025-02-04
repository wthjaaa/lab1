import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ScaniaTest extends CarTest{

    @Override
    protected Car createCar(){
        return new Scania(2,400, java.awt.Color.RED, "Scania");
    }

    @Test
    void testRaiseWhileMoving() {
        Scania scania = (Scania) car;
        car.startEngine();
        car.gas(0.5);

        IllegalStateException exception = assertThrows(IllegalStateException.class, scania::raiseFlak);
    }

    @Override
    @Test
    void testSpeedFactor() {
        double expectedSpeedFactor = car.getEnginePower()*0.1;

        assertEquals(expectedSpeedFactor, car.speedFactor(), 0.01, "Hastigheten bör räknas korrekt");


    }


}
