import java.io.*;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.logging.Level;

public class Nimbus {
    // Came up with the name and used an online ASCII art generator
    // Generator: https://patorjk.com/software/taag/#p=display&h=0&v=0&f=Ghost&t=nimbus
    private static String logo = "\n" +
            "     .-') _             _   .-')    .-. .-')                  .-')    \n" +
            "    ( OO ) )           ( '.( OO )_  \\  ( OO )                ( OO ).  \n" +
            ",--./ ,--,'    ,-.-')   ,--.   ,--.) ;-----.\\   ,--. ,--.   (_)---\\_) \n" +
            "|   \\ |  |\\    |  |OO)  |   `.'   |  | .-.  |   |  | |  |   /    _ |  \n" +
            "|    \\|  | )   |  |  \\  |         |  | '-' /_)  |  | | .-') \\  :` `.  \n" +
            "|  .     |/    |  |(_/  |  |'.'|  |  | .-. `.   |  |_|( OO ) '..`''.) \n" +
            "|  |\\    |    ,|  |_.'  |  |   |  |  | |  \\  |  |  | | `-' /.-._)   \\ \n" +
            "|  | \\   |   (_|  |     |  |   |  |  | '--'  / ('  '-'(_.-' \\       / \n" +
            "`--'  `--'     `--'     `--'   `--'  `------'    `-----'     `-----'  \n";

    private static final String horizontalLine = "\n-------------------------------------------------";
    private static final String welcomeMessage = "Hello from \n" + logo + horizontalLine +
            " \nHow can I help you today~ UwU" + horizontalLine;;
    private static final String endingMessage = "BAIBAI! NIMBUS WEEEEEEEEE" + horizontalLine;


    private static final Logger logger = Logger.getLogger(Nimbus.class.getName());
    private static Scanner scanner = new Scanner(System.in);
    private static TaskList taskList = new TaskList();
    private static final String filepath = "src/main/data/nimbus.txt";
    private static final String dirpath = "src/main/data";
    private static Storage storage = new Storage(filepath, dirpath);

    private static ArrayList<Task> tasks = taskList.getTaskList();

    public static void main(String[] args)
            throws WrongInputException, MissingDescriptionException,
            MissingDeadlineException, MissingStartEndTimeException, IOException {

        System.out.println(welcomeMessage);
        Storage.createFile();
        Storage.loadFile(taskList);

        String userInput ="";

        while (!userInput.equals("bye")) {
            userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                Storage.updateFile(tasks);
                System.out.println("Nimbus: " + endingMessage);
            } else if (userInput.equals("list")) {
                taskList.toString();
            } else if (userInput.startsWith("mark ")) {
                int x = Integer.parseInt(userInput.substring(5).trim());
                taskList.completeTask(x - 1);
            } else if (userInput.startsWith("unmark ")) {
                int x = Integer.parseInt(userInput.substring(7).trim());
                taskList.incompleteTask(x - 1);
            } else if (userInput.startsWith("delete ")) {
                int x = Integer.parseInt(userInput.substring(7).trim());
                taskList.deleteTask(x - 1);
            } else {
                taskList.addTask(userInput);
            }
        }
        scanner.close();
    }
}