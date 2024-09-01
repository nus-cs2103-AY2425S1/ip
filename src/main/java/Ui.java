import java.io.IOException;
import java.util.Scanner;

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

    private static final String horizontalLine = "\n-------------------------------------------------";
    private static final String welcomeMessage = "Hello from \n" + logo + horizontalLine +
            " \nHow can I help you today~ UwU" + horizontalLine;;
    private static final String endingMessage = "BAIBAI! NIMBUS WEEEEEEEEE" + horizontalLine;
    private static final Scanner scanner = new Scanner(System.in);
    private final TaskList taskList;

    public Ui(TaskList taskList) {
        this.taskList = taskList;
    }

    public static void showWelcome() {
        System.out.println(welcomeMessage);
    }

    public static void goodbyeMessage() {
        System.out.println(endingMessage);
    }

    public void run() throws MissingStartEndTimeException, MissingDeadlineException,
            MissingDescriptionException, WrongInputException, WrongDateTimeFormatException, IOException {

        Parser parser = new Parser(taskList);
        String userInput ="";

        while (!userInput.equals("bye")) {
            userInput = scanner.nextLine();
            parser.handleInput(userInput);
        }
        scanner.close();
    }

}
