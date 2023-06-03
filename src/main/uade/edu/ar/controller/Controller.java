package main.uade.edu.ar.controller;

public class Controller {

    private static Controller controller;

    private Controller() {
    }

    public static synchronized Controller getInstance() {
        if (controller == null) {
            controller = new Controller();
        }
        return controller;
    }
}
