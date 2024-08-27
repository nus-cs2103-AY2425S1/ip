import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * The Quack chatbot program implements the functionality needed
 * to help users keep track of tasks for them.
 */
public class Quack {

    /** String to print out the spacers between each command */
    public String spacer = "-".repeat(70);
    /** Date time format for printing LocalDateTime objects */
    public DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    /** The name of the chatbot */
    private String botName = "Quack";
    /** The logo for the chatbot */
    private String logo =
        "________                       __    \n" +
        "\\_____  \\  __ _______    ____ |  | __\n" +
        " /  / \\  \\|  |  \\__  \\ _/ ___\\|  |/ /\n" +
        "/   \\_/.  \\  |  // __ \\\\  \\___|    < \n" +
        "\\_____\\ \\_/____/(____  /\\___  >__|_ \\ \n" +
        "       \\__>          \\/     \\/     \\/\n";
    /** Scanner object to take in user inputs */
    public Scanner sc = new Scanner(System.in);
    /** To store all of the users tasks */
    private TaskList toDoList;
    /** Determine if the bot should continue or stop running */
    private boolean isRunning;
    /** All available types of tasks the chatbot supports */
    enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }
    /** Sotrage object to load and save data */
    private Storage storage;
    /** Paser object to handle user inputs */
    private Paser paser;
    
    /**
     * Creates a Quack chatbot object.
     */
    Quack() {

        this.isRunning = true;
        this.toDoList = new TaskList();
        this.storage = new Storage(this.toDoList);
        this.paser = new Paser();
    }

    /**
     * Prints the logo of Quack.
     */
    private void printLogo() {

        System.out.println(logo + "\n" + spacer);
    }

    /**
     * Prints the greeting message for Quack.
     */
    private void Printgreeting() {

        System.out.println("Hello! I'm " + botName + "\nWhat can I do for you?\n" + spacer);
    }

    /**
     * Prints the farewell message for Quack.
     */
    private void printFarewell() {
        
        this.isRunning = false;
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Stops quack from taking more inputs from the user and stops the chatbot.
     */
    public void shutDown() {
        this.isRunning = false;
    }


    /**
     * Runs the chatbot and start taking inputs from the user.
     */
    private void run() {

        // Chatbot is running for the first time, display the logo and greet the user.
        this.printLogo();
        this.Printgreeting();
        
        // Keep taking inputs from the user as long as the chatbot is running
        while (isRunning) {
            try {
                Command command = paser.getUserInput(this);
                command.execute(this, toDoList, storage);
            } catch (InvalidCommandException commandError) {
                System.out.println(commandError.getMessage());
            }
        }

        this.printFarewell();
        // Close the scanner
        this.sc.close();
    }

    public static void main(String[] args) {    
        Quack bot = new Quack();
        bot.run();
    }
}
