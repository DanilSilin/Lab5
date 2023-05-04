package server.commands;

import server.commands.list.*;
import server.controller.VehicleController;
import server.controller.VehicleControllerImpl;
import server.exceptions.ArgumentException;
import server.exceptions.FileException;
import server.exceptions.ValidationException;
import server.services.ScriptManager;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import static server.utils.Parser.tildaResolver;

public class Invoker {
    private static Map<String, Command> commandMap = new HashMap<>();
    private final VehicleController controller;
    private final ScriptManager scriptManager;
    private final String fileName;
    private BufferedReader reader;

    public Invoker(String filename) {
        this.reader = reader == null ? new BufferedReader(new InputStreamReader(System.in)) : reader;
        this.fileName = tildaResolver(filename);
        controller = new VehicleControllerImpl(fileName);
        scriptManager = new ScriptManager(null);
        init();
    }

    public void addCommandToMap(String commandName, Command command) {
        commandMap.put(commandName, command);
    }

    public void init() {
        addCommandToMap("help", new HelpCommand());
        addCommandToMap("exit", new ExitCommand());
        addCommandToMap("info", new InfoCommand(controller));
        addCommandToMap("clear", new ClearCommand(controller));
        addCommandToMap("show", new ShowCommand(controller));
        addCommandToMap("add", new AddCommand(controller));
        addCommandToMap("update", new UpdateCommand(controller));
        addCommandToMap("remove_by_id", new RemoveByIdCommand(controller));
        addCommandToMap("remove_at", new RemoveAtCommand(controller));
        addCommandToMap("remove_lower", new RemoveLowerCommand(controller));
        addCommandToMap("reorder", new ReorderCommand(controller));
        addCommandToMap("min_by_number_of_wheels", new MinByNumOfWheelsCommand(controller));
        addCommandToMap("print_ascending", new PrintAscendingCommand(controller));
        addCommandToMap("group_counting_by_name", new GroupCountingByNameCommand(controller));
        addCommandToMap("save", new SaveCommand(controller));
        addCommandToMap("execute_script", new ExecuteScriptCommand(this, scriptManager));
    }

    public void execute(String input) {
        try {
            String[] commandArray = input.split(" ");
            String command = commandArray[0];
            for (Map.Entry<String, Command> pair : Invoker.getCommandMap().entrySet()) {
                if (pair.getKey().equals(command)) {
                    pair.getValue().execute(commandArray);
                }
            }
            if (!Invoker.getCommandMap().containsKey(command)) {
                System.out.println("Command not found.");
            }
        } catch (ArgumentException | ValidationException | FileException e) {
            System.out.println(e.getMessage());
        } catch (NoSuchElementException e){
            System.out.println("Завешение программы...");
            System.exit(1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public BufferedReader getReader() {
        return reader;
    }

    public void setReader(BufferedReader reader) {
        this.reader = reader;
    }

    public static Map<String, Command> getCommandMap() {
        return commandMap;
    }

    public static void setCommandMap(Map<String, Command> commandMap) {
        Invoker.commandMap = commandMap;
    }
}