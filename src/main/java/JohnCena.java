import storage.Storage;
import tasks.Task;
import exceptions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import parser.InputHandler;
import commands.HelloCommand;


public class JohnCena {
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static InputHandler inputHandler;


    public static void main(String[] args) {

        tasks = Storage.loadTasks();
        inputHandler = new InputHandler(tasks);

        Scanner scanner;
        if (args.length > 0) {
            try {
                scanner = new Scanner(new File(args[0]));
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
                return;
            }
        } else {
            scanner = new Scanner(System.in);
        }
        HelloCommand helloCommand = new HelloCommand();
        helloCommand.execute();

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            try {
                inputHandler.handleInput(input);
            } catch (CenaException e) {
                System.out.println("____________________________________________________________");
                System.out.println(" OOPS!!! " + e.getMessage());
                System.out.println("____________________________________________________________");
            }
        }

        scanner.close();
    }

}