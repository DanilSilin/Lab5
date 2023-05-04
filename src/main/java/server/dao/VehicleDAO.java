package server.dao;

import server.model.FuelType;
import server.model.Vehicle;

import java.util.List;
import java.util.Map;

public interface VehicleDAO {
    String info();

    Vehicle getVehicleById(int id);

    List<Vehicle> getAllVehicle();

    Integer addVehicle(Vehicle vehicle);

    Vehicle updateVehicle(Vehicle vehicle, int id);

    void removeVehicleById(int id);

    void clear();

    void save();

    void removeAt(int index);

    void removeLower(int enginePower);

    List<Vehicle> reorder();

    Vehicle minByNumberOfWheels();
    Map<String, Integer> groupCountingByName();
    List<Vehicle> printAscending();
}
