package server.utils;

import server.model.FuelType;
import server.model.VehicleType;

public class Parser {
    public static String tildaResolver(String file) {
        if (file.startsWith("~")) {
            file = file.replaceFirst("^~", System.getProperty("user.home"));
        }
        return file;
    }

    public static FuelType fromStringToFuelType(String line) {
        switch (line.toLowerCase()) {
            case "1", "gasoline" -> {
                return FuelType.GASOLINE;
            }
            case "2", "alcohol" -> {
                return FuelType.ALCOHOL;
            }
            case "3", "antimatter" -> {
                return FuelType.ANTIMATTER;
            }
            case "4", "plasma" -> {
                return FuelType.PLASMA;
            }
            default -> {
                return FuelType.GASOLINE;
            }
        }
    }

    public static VehicleType fromStringToVehicleType(String line) {
        switch (line.toUpperCase()) {
            case "1", "DRONE" -> {
                return VehicleType.DRONE;
            }
            case "2", "SUBMARINE" -> {
                return VehicleType.SUBMARINE;
            }
            case "3", "MOTORCYCLE" -> {
                return VehicleType.MOTORCYCLE;
            }
            case "4", "HOVERBOARD" -> {
                return VehicleType.HOVERBOARD;
            }
            case "5", "SPACESHIP" -> {
                return VehicleType.SPACESHIP;
            }
            default -> {
                return VehicleType.DRONE;
            }
        }
    }
}
