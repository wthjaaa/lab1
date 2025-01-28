import org.junit.jupiter.api.Test;

public class Volvo240Test extends CarTest {

    @Override
    protected Car createCar() {
        return new Volvo240Mod();
    }
}
