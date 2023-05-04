package server.commands.list;

import server.commands.Command;
import server.controller.VehicleController;
import server.exceptions.ArgumentException;

public class ShowCommand implements Command {
    private final VehicleController controller;

    public ShowCommand(VehicleController controller) {
        this.controller = controller;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 1) {
            throw new ArgumentException("Команда не должна содержать аргументов.");
        }
        controller.getAllVehicle().forEach(System.out::println);
        System.out.println("Выведено " + controller.getAllVehicle().size() + " объектов.");
    }

    @Override
    public String description() {
        return "выводит все объекты.";
    }
}
