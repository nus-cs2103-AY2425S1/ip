package nimbus.ui;

/**
 * Class that contains some of the UI elements needed for the project
 */

public class Ui {
    public static final String HORIZONTAL_LINE = "\n-------------------------------------------------";
    // Came up with the name and used an online ASCII art generator
    // Generator: https://patorjk.com/software/taag/#p=display&h=0&v=0&f=Ghost&t=nimbus
    private static String logo = "\n"
            + "     .-') _             _   .-')    .-. .-')                  .-')    \n"
            + "    ( OO ) )           ( '.( OO )_  \\  ( OO )                ( OO ).  \n"
            + ",--./ ,--,'    ,-.-')   ,--.   ,--.) ;-----.\\   ,--. ,--.   (_)---\\_) \n"
            + "|   \\ |  |\\    |  |OO)  |   `.'   |  | .-.  |   |  | |  |   /    _ |  \n"
            + "|    \\|  | )   |  |  \\  |         |  | '-' /_)  |  | | .-') \\  :` `.  \n"
            + "|  .     |/    |  |(_/  |  |'.'|  |  | .-. `.   |  |_|( OO ) '..`''.) \n"
            + "|  |\\    |    ,|  |_.'  |  |   |  |  | |  \\  |  |  | | `-' /.-._)   \\ \n"
            + "|  | \\   |   (_|  |     |  |   |  |  | '--'  / ('  '-'(_.-' \\       / \n"
            + "`--'  `--'     `--'     `--'   `--'  `------'    `-----'     `-----'  \n";
    private static final String welcomeMessage = "Hello from \n" + logo + HORIZONTAL_LINE
            + " \nHow can I help you today~ UwU" + HORIZONTAL_LINE;;
    private static final String endingMessage = "BAIBAI! NIMBUS WEEEEEEEEE" + HORIZONTAL_LINE;
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
    public static String goodbyeMessage() {
        return endingMessage;
    }
}
