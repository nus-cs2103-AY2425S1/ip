package johncena;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import johncena.commands.Command;
import johncena.commands.HelloCommand;
import johncena.exceptions.CenaException;
import johncena.parser.InputHandler;
import johncena.storage.Storage;
import johncena.tasks.Task;



/**
 * The {@code JohnCena} class is the main class of the John Cena program.
 */
public class JohnCena {
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static InputHandler inputHandler;

    /**
     * Initializes the John Cena program.
     */
    public JohnCena() {
        tasks = Storage.loadTasks();
        inputHandler = new InputHandler(tasks);
        HelloCommand helloCommand = new HelloCommand();
        helloCommand.execute();
    }


//
//    /**
//     * The main entry point of the John Cena program.
//     *
//     * @param args the command-line arguments
//     */
//    public static void main(String[] args) {
//
//        tasks = Storage.loadTasks();
//        inputHandler = new InputHandler(tasks);
//
//        Scanner scanner;
//        if (args.length > 0) {
//            try {
//                scanner = new Scanner(new File(args[0]));
//            } catch (FileNotFoundException e) {
//                System.out.println("File not found");
//                return;
//            }
//        } else {
//            scanner = new Scanner(System.in);
//        }
//        HelloCommand helloCommand = new HelloCommand();
//        helloCommand.execute();
//
//        while (scanner.hasNextLine()) {
//            String input = scanner.nextLine();
//            try {
//                inputHandler.handleInput(input);
//            } catch (CenaException e) {
//                System.out.println("____________________________________________________________");
//                System.out.println(" OOPS!!! " + e.getMessage());
//                System.out.println("____________________________________________________________");
//            }
//        }
//
//        scanner.close();
//    }

    /**
     * Generates a response for the user's chat message.
     *
     * @param input the user input
     * @return the response
     */
    public String getResponse(String input) {
        assert input != null : "Input should not be null";
        try {
            Command command = inputHandler.handleInput(input);
            return command.execute();
            //return "Command executed successfully.";
        } catch (CenaException e) {
            return "OOPS!!! " + e.getMessage();
        }
    }

}
