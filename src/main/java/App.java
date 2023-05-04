import client.ConsoleUI;
import server.commands.Invoker;
import server.exceptions.FileException;

import java.io.File;

public class App {
    public static void main(String[] args) {
        try {
            ConsoleUI session = new ConsoleUI(new Invoker(args[0]));
            session.start();
        } catch (FileException e){
            System.out.println(e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Необходимо ввести название файла базы данных при запуске программы.");
        }
    }
}
