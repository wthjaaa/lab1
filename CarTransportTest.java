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
            transport.loadCar(volvo);
        }
        assertThrows(IllegalStateException.class, () -> transport.loadCar(volvo), "Fartyget är fullt, bör inte kunna lasta mera");
    }

    @Test
    void PositionSync() {
        transport.lowerRamp();
        transport.loadCar(volvo);

        assertEquals(transport.getX(), volvo.getX(), "X positionen bör vara samma första fartyget och bilen");
        assertEquals(transport.getY(), volvo.getY(), "Y positionen bör vara samma första fartyget och bilen");
    }



}
