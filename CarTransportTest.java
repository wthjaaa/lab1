import java.awt.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

class CarTransportTest {
    private CarTransport transport;
    private Car volvo;


    @BeforeEach
    void setUp() {
        transport = new CarTransport(Color.BLACK, 300);
        volvo = new Volvo240();
        volvo.setPosition(0,0);
    }

    @Test
    void LowerCar() {
        transport.lowerRamp();
        assertTrue(transport.isRampDown(), "Rampen bör vara nere");
    }

    @Test
    void RaiseRamp() {
        transport.lowerRamp();
        transport.raiseRamp();
        assertFalse(transport.isRampDown(), "Rampen bör vara uppe");
    }

    @Test
    void UnloadCar() {
        transport.lowerRamp();
        transport.loadCar(volvo);
        Car unloadedCar = transport.unloadCar();
        assertEquals(volvo, unloadedCar, "Sista bilen in, första bilen ut");
    }

    @Test
    void MoveRampDown() {
        transport.lowerRamp();
        assertThrows(IllegalStateException.class, transport::move);
    }

    @Test
    void MoreThanMaxCars() {
        transport.lowerRamp();

        for (int i = 0; i < 8; i++) {
            volvo = new Volvo240();
            volvo.setPosition(transport.getX(), transport.getY()); // Place near transport
            transport.loadCar(volvo);
        }


        Car extraCar = new Volvo240();
        extraCar.setPosition(transport.getX(), transport.getY());

        assertThrows(IllegalStateException.class, () -> transport.loadCar(extraCar),
                "Fartyget är fullt, bör inte kunna lasta mera");
    }


    @Test
    void PositionSync() {
        transport.lowerRamp();
        transport.loadCar(volvo);

        assertEquals(transport.getX(), volvo.getX(), "X positionen bör vara samma första fartyget och bilen");
        assertEquals(transport.getY(), volvo.getY(), "Y positionen bör vara samma första fartyget och bilen");
    }

    @Test
    void PreventDuplicateCarInMultipleTransports() {
        CarTransport transport2 = new CarTransport(Color.RED, 250);
        transport2.setPosition(1, 1);

        transport.lowerRamp();
        transport.loadCar(volvo);

        transport2.lowerRamp();
        Exception exception = assertThrows(IllegalStateException.class, () -> transport2.loadCar(volvo),
                "Bilen bör inte kunna lastas i en annan transportbil om den redan är lastad i en");

        assertTrue(transport.isCarLoaded(volvo), "Bilen ska vara i första transportbilen");
        assertFalse(transport2.isCarLoaded(volvo), "Bilen ska INTE vara i andra transportbilen");
    }





}
