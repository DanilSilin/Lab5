package server.commands.list;

import server.commands.Command;
import server.controller.VehicleController;
import server.exceptions.ArgumentException;
import server.exceptions.ValidationException;
import server.model.Vehicle;

public class RemoveByIdCommand implements Command {
    private final VehicleController controller;

    public RemoveByIdCommand(VehicleController controller) {
        this.controller = controller;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 2) {
            throw new ArgumentException("Команда содержит 1 аргумент - id.");
        }
        int id;
        try {
            id = Integer.parseInt(args[1]);
            if (id <= 0) {
                throw new ValidationException("Id больше нуля");
            }
            if (controller.getAllVehicle().stream().map(Vehicle::getId).toList().contains(id)) {
                controller.removeVehicleById(id);
                System.out.println("Vehicle удален.");
            } else {
                System.out.println("Vehicle с таким id не найден.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Id принимает целочисленное значение.");
        }
    }

    @Override
    public String description() {
        return "удаляет элемент по id.";
    }
}
