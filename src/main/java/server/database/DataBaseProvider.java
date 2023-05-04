package server.database;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import server.model.Vehicle;


import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static server.validation.Validation.*;

public class DataBaseProvider {
    private final List<Vehicle> dataBase;
    private final LocalDateTime dateTime;
    private final String fileName;

    public DataBaseProvider(String filename) {
        validateFile(filename);
        this.fileName = filename;
        this.dataBase = loadDataBase(filename);
        this.dateTime = LocalDateTime.now();
    }
    private static List<Vehicle> validatedList(List<Vehicle> data){
        try {
            List<Vehicle> copy = new ArrayList<>(data);
            ArrayList<Integer> idList = new ArrayList<>();
            for (Vehicle vehicle : copy) {
                if (!validateVehicle(vehicle, idList)){
                    data.remove(vehicle);
                }
                idList.add(vehicle.getId());
            }
            return data;
        } catch (NullPointerException e){
            System.out.println("Список пуст.");
            return new LinkedList<>();
        }
    }

    private static List<Vehicle> loadDataBase(String fileName) {
        List<Vehicle> resultSet = new LinkedList<>();
        try {
            isValidJsonFile(fileName);
            Gson gson = new Gson();
            Type type = new TypeToken<LinkedList<Vehicle>>(){}.getType();
            JsonReader reader = new JsonReader(new FileReader(fileName));
            resultSet = gson.fromJson(reader, type);
            return validatedList(resultSet);
        } catch (IOException e) {
            System.out.println("Ошибка при чтение json файла.");
            return new LinkedList<>();
        }
    }
    public void save(){
        validateFile(fileName);
        try (Writer writer = new FileWriter(fileName)){
            Gson gson = new GsonBuilder().create();
            gson.toJson(dataBase, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private synchronized Long generateNextId() {
        return dataBase.stream().mapToLong(Vehicle::getId).max().isPresent()
                ? dataBase.stream().mapToLong(Vehicle::getId).max().getAsLong() + 1
                : 1;
    }

    public int addVehicleToDatabase(Vehicle model) {
        int id = generateNextId().intValue();
        model.setId(id);
        dataBase.add(model);
        return id;
    }

    public boolean removeVehicleFromDataBase(int id) {
        List<Vehicle> copy = new LinkedList<>(dataBase);
        for (Vehicle model : copy) {
            if (model.getId().equals(id)) {
                dataBase.remove(model);
                return true;
            }
        }
        return false;
    }


    public List<Vehicle> getDataBase() {
        return dataBase;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
}
