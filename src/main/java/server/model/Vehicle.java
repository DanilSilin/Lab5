package server.model;

import com.google.gson.annotations.JsonAdapter;
import server.services.GsonLocalDateTime;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Vehicle implements Comparable<Vehicle>{
    private Integer id;
    private String name;
    private Coordinates coordinates;
    @JsonAdapter(GsonLocalDateTime.class)
    private java.time.LocalDateTime creationDate;
    private int enginePower;
    private int numberOfWheels;
    private VehicleType type;
    private FuelType fuelType;

    public Vehicle(String name, Coordinates coordinates, LocalDateTime creationDate, int enginePower, int numberOfWheels, VehicleType type, FuelType fuelType) {
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.enginePower = enginePower;
        this.numberOfWheels = numberOfWheels;
        this.type = type;
        this.fuelType = fuelType;
    }

    public Vehicle(Integer id, String name, Coordinates coordinates, LocalDateTime creationDate, int enginePower, int numberOfWheels, VehicleType type, FuelType fuelType) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.enginePower = enginePower;
        this.numberOfWheels = numberOfWheels;
        this.type = type;
        this.fuelType = fuelType;
    }

    public Vehicle() {
        creationDate = LocalDateTime.now();
    }
    public Vehicle update(Vehicle vehicle){
        name = vehicle.getName();
        coordinates = vehicle.getCoordinates();
        enginePower = vehicle.getEnginePower();
        numberOfWheels = vehicle.getNumberOfWheels();
        type = vehicle.getType();
        fuelType = vehicle.getFuelType();
        return this;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public int getEnginePower() {
        return enginePower;
    }

    public void setEnginePower(int enginePower) {
        this.enginePower = enginePower;
    }

    public int getNumberOfWheels() {
        return numberOfWheels;
    }

    public void setNumberOfWheels(int numberOfWheels) {
        this.numberOfWheels = numberOfWheels;
    }

    public VehicleType getType() {
        return type;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", enginePower=" + enginePower +
                ", numberOfWheels=" + numberOfWheels +
                ", type=" + type +
                ", fuelType=" + fuelType +
                '}';
    }

    @Override
    public int compareTo(Vehicle o) {
        return enginePower - o.getEnginePower();
    }
}