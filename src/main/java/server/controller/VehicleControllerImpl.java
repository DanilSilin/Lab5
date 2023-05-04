package server.controller;

import server.dao.VehicleDAO;
import server.dao.VehicleDAOImpl;
import server.model.FuelType;
import server.model.Vehicle;

import java.util.List;
import java.util.Map;

import static server.validation.Validation.validateFile;

public class VehicleControllerImpl implements VehicleController {
    private VehicleDAO vehicleDAO;

    public VehicleControllerImpl(String fileName) {
        this.vehicleDAO = new VehicleDAOImpl(fileName);
    }

    @Override
    public String info() {
        return vehicleDAO.info();
    }

    @Override
    public Vehicle getVehicleById(int id) {
        return vehicleDAO.getVehicleById(id);
    }

    @Override
    public List<Vehicle> getAllVehicle() {
        return vehicleDAO.getAllVehicle();
    }

    @Override
    public Integer addVehicle(Vehicle vehicle) {
        return vehicleDAO.addVehicle(vehicle);
    }

    @Override
    public Vehicle updateVehicle(Vehicle vehicle, int id) {
        return vehicleDAO.updateVehicle(vehicle, id);
    }

    @Override
    public void removeVehicleById(int id) {
        vehicleDAO.removeVehicleById(id);
    }

    @Override
    public void clear() {
        vehicleDAO.clear();
    }

    @Override
    public void save() {
        vehicleDAO.save();
    }

    @Override
    public void removeAt(int index) {
        vehicleDAO.removeAt(index);
    }

    @Override
    public void removeLower(int enginePower) {
        vehicleDAO.removeLower(enginePower);
    }

    @Override
    public List<Vehicle> reorder() {
        return vehicleDAO.reorder();
    }

    @Override
    public Vehicle minByNumberOfWheels() {
        return vehicleDAO.minByNumberOfWheels();
    }

    @Override
    public Map<String, Integer> groupCountingByName() {
        return vehicleDAO.groupCountingByName();
    }

    @Override
    public List<Vehicle> printAscending() {
        return vehicleDAO.printAscending();
    }

    public VehicleDAO getVehicleDAO() {
        return vehicleDAO;
    }

    public void setVehicleDAO(VehicleDAO vehicleDAO) {
        this.vehicleDAO = vehicleDAO;
    }
}
