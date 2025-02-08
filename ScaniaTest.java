import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ScaniaTest extends TruckTest{

    @Override
    protected Truck createTruck(){
        return new Scania();
    }

    @Test
    void testRaiseWhileMoving() {
        Scania scania = (Scania) truck;
        truck.startEngine();
        truck.gas(0.5);

        IllegalStateException exception = assertThrows(IllegalStateException.class, scania::raiseFlak);
    }

    @Override
    @Test
    void testSpeedFactor() {
        double expectedSpeedFactor = truck.getEnginePower()*0.1;

        assertEquals(expectedSpeedFactor, truck.speedFactor(), 0.01, "Hastigheten bör räknas korrekt");


    }


}
