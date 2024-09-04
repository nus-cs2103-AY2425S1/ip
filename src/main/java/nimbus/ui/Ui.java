package nimbus.ui;

import nimbus.exception.*;

import java.io.IOException;
import java.util.Scanner;

/**
 * Class that contains some of the UI elements needed for the project
 */

public class Ui {
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

    public static final String horizontalLine = "\n-------------------------------------------------";
    private static final String welcomeMessage = "Hello from \n" + logo + horizontalLine +
            " \nHow can I help you today~ UwU" + horizontalLine;;
    private static final String endingMessage = "BAIBAI! NIMBUS WEEEEEEEEE" + horizontalLine;
    private static final Scanner scanner = new Scanner(System.in);
    private final TaskList taskList;

    /**
     * Creates a UI object that contains the taskList that the project is working on
     *
     * @param taskList the tasklist that the project is working on
     */

    public Ui(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Prints out welcome message to user when user first enters chatbot
     */

    public static void showWelcome() {
        System.out.println(welcomeMessage);
    }

    /**
     * Prints out goodbye message when user exits the chatbot
     */

    public static void goodbyeMessage() {
        System.out.println(endingMessage);
    }

    /**
     * Starts to handle userInput by passing to parser
     *
     * @throws WrongDateTimeFormatException if date time format is wrong
     * @throws IOException if file is not found
     */

    public void run() throws WrongDateTimeFormatException, IOException {

        Parser parser = new Parser(taskList);
        String userInput ="";

        while (!userInput.equals("bye")) {
            userInput = scanner.nextLine();
            parser.handleInput(userInput);
        }
        scanner.close();
    }

}
