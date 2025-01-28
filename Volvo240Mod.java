import  java.awt.*;

public class Volvo240Mod extends Car {
    private final static double trimFactor = 1.25;

    public Volvo240Mod() {
        super(4, 100, Color.BLACK, "Volvo240");
    }

    @Override
    public double speedFactor() {
        return getEnginePower()*0.01*trimFactor;
    }
}
