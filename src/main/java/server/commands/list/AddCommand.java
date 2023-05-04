package server.commands.list;

import server.builders.VehicleBuilder;
import server.commands.Command;
import server.controller.VehicleController;
import server.exceptions.ArgumentException;

public class AddCommand implements Command {
    private final VehicleController controller;

    public AddCommand(VehicleController controller) {
        this.controller = controller;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 1) {
            throw new ArgumentException("Команда не должна содержать аргументов.");
        }
        controller.addVehicle(VehicleBuilder.build());
        System.out.println("Успешно добавлен!");
    }

    @Override
    public String description() {
        return "добавить новый элемент в коллекцию.";
    }
}
