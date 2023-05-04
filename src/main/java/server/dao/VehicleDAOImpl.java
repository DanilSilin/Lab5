package server.dao;

import server.database.DataBaseProvider;
import server.model.Vehicle;

import java.util.*;

public class VehicleDAOImpl implements VehicleDAO {
    private final DataBaseProvider source;

    public VehicleDAOImpl(String fileName) {
        this.source = new DataBaseProvider(fileName);
    }

    @Override
    public String info() {
        String answer = ("Данные о базе данных: \n");
        answer += "Тип: " + source.getDataBase().getClass().getTypeName().split("\\.")[2] + "\n";
        answer += "Время создания: " + source.getDateTime().toString() + "\n";
        answer += "Элементов внутри: " + (source.getDataBase().size()) + "\n";
        return answer;
    }

    @Override
    public Vehicle getVehicleById(int id) {
        //не трогать
        return null;
    }

    @Override
    public List<Vehicle> getAllVehicle() {
        return source.getDataBase();
    }

    @Override
    public Integer addVehicle(Vehicle vehicle) {
        return source.addVehicleToDatabase(vehicle);
    }

    @Override
    public Vehicle updateVehicle(Vehicle vehicle, int id) {
        return source.getDataBase().stream().filter(v -> v.getId().equals(id)).findFirst().get().update(vehicle);
    }

    @Override
    public void removeVehicleById(int id) {
        source.removeVehicleFromDataBase(id);
    }

    @Override
    public void clear() {
        source.getDataBase().clear();
    }

    @Override
    public void save() {
        source.save();
    }

    @Override
    public void removeAt(int index) {
        source.getDataBase().remove(index);
    }

    @Override
    public void removeLower(int enginePower) {
        source.getDataBase().removeIf(v -> v.getEnginePower() < enginePower);
    }

    @Override
    public List<Vehicle> reorder() {
        Collections.reverse(source.getDataBase());
        return source.getDataBase();
    }

    @Override
    public Vehicle minByNumberOfWheels() {
        return source.getDataBase().stream().min(Comparator.comparing(Vehicle::getNumberOfWheels)).orElse(null);
    }

    @Override
    public Map<String, Integer> groupCountingByName() {
        Map<String, Integer> response = new HashMap<>();
        for (Vehicle vehicle : source.getDataBase()) {
            response.merge(vehicle.getName(), 1, Integer::sum);
        }
        return response;
    }

    @Override
    public List<Vehicle> printAscending() {
        return source.getDataBase().stream().sorted().toList();
    }
}
