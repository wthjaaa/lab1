import java.awt.*;

public class WorkshopStats {
    public static void main(String[] args) {
        VolvoWorkshop volvoworkshop = new VolvoWorkshop(5);

        volvoworkshop.loadCar(new Volvo240()); // Allowed
        // volvoworkshop.loadCar(new Saab95()); // complie-time error!

        Workshop<Vehicle> generalWorkshop = new Workshop<>(10);
        generalWorkshop.loadCar(new Saab95()); // Allowed
        generalWorkshop.loadCar(new Scania()); // Allowed

        Volvo240 unloadedVolvo = volvoworkshop.unloadCar();
        Vehicle unloadedGeneralCar = generalWorkshop.unloadCar();

        System.out.println("Unloaded Vehicle from Volvo workshop: " + unloadedVolvo.getClass().getSimpleName());
        System.out.println("Unloaded Vehicle from General workshop: " + unloadedGeneralCar.getClass().getSimpleName());

    }


}
