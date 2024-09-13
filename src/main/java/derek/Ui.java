package derek;

import java.time.format.DateTimeParseException;
import java.util.Random;
import java.util.Scanner;

import derek.command.Command;
import derek.exception.IncorrectCommandException;
import derek.task.Task;
import derek.task.TaskList;
import javafx.application.Platform;


/**
 * The {@code Ui} class handles user interaction in the application.
 * It manages input/output operations and facilitates communication between the user and the system.
 */
public class Ui {
    private static String logo = " ---      ---\n"
            + "|  #  |   |  #  |\n"
            + "  ---     ---\n"
            + "   \\            /\n"
            + "      ----\n";

    private static String sadLogo = " ---      ---\n"
            + "| # |  | # |\n"
            + " ---    ---\n"
            + "      ----\n"
            + "   /             \\\n";
    private static String leavingMessage = String.format("Ok...\n" + sadLogo);

    private Storage storage;
    private TaskList taskList;

    /**
     * Constructs a {@code Ui} object with the specified storage and task list.
     *
     * @param storage the storage object that manages task persistence
     * @param taskList the task list that holds the tasks
     */
    public Ui(Storage storage, TaskList taskList) {
        this.storage = storage;
        this.taskList = taskList;
    }

    /**
     * Introduces Derek and initiates user interaction to become friends.
     */
    public String introduce() {
        return "Hello! I'm Derek! Can we be friends?\n" + logo + "\nYour response (Y/N):";

    }

    /**
     * Prompts the user to enter their name and starts the main user interaction loop.
     */
    public String getUserName() {
        return "Great! I have always wanted a friend!\n"
                + "What do I call you?";

    }

    /**
     * Displays a welcome message and provides instructions for the user to enter commands.
     * It then starts accepting user commands.
     */
    public String initiateUserInteraction(String user) {
        return "\n" + "Hi! " + user
                + "! So, I guess as a friend I become your little slave!\n"
                + "What do you want me to do?\n"
                + "-------------------------------------------\n"
                + "Please enter your commands correctly for Derek (he's a little slow):\n"
                + "todo (task)\n"
                + "event (task) /from (DD/MM/YYYY HH:MM) /to (DD/MM/YYYY HH:MM))\n"
                + "deadline (task) /by (DD/MM/YYYY HH:MM)\n"
                + "mark (task number)\n"
                + "unmark (task number)\n"
                + "delete (task number)\n"
                + "find (keyword)\n"
                + "bye";
    }

    /**
     * Accepts commands from the user and processes them.
     */
    public String processCommands(String command) throws IncorrectCommandException, DateTimeParseException  {
        Parser parser = new Parser(command, this.storage, this);
        return parser.getCommand();

    }

    public String processConsent(String command) throws IncorrectCommandException {
        Parser parser = new Parser(command, this.storage, this);
        return parser.getConsent();
    }

    public String printLeavingMessage() {
        Platform.exit();
        return leavingMessage;
    }

    public String returnList() {
        return "I think these are your tasks! Please don't leave me!!\n"
                + this.taskList;
        /**
        System.out.println("I think these are your tasks! Please don't leave me!!\n"
                + this.taskList);
         */
    }

    public String removeTask(Task task) {
        return "phew! that list was looooonngggg... i was getting tired of remembering it!"
                + "\n"
                + task.toString();

    }

    public String completeTask(Task task) {
        String celebration = generateRandomCelebration();
        return celebration + " you slayed that!" + "\n" + task.toString();
    }

    public String incompleteTask(Task task) {
        return "You'll get 'em next time!" + "\n" + task.toString();
        //System.out.println("You'll get 'em next time!" + "\n" + task.toString());
    }

    public String addTask(Task task) {
        String celebration = generateRandomCelebration();
        return celebration + "\n" + task + "\n";
        //System.out.println(celebration + "\n" + task + "\n");
    }

    public String printTask(Task task) {
        return "Here you are!" + "\n" + task;
        //System.out.println("Here you are!" + "\n" + task);
    }

    public String generateRandomCelebration() {
        String[] celebrationMessages = new String[]{"yay!",
            "woohoo!",
            "let's go!!!!",
            "great job :)",
            "you're on a roll!"};
        Random random = new Random();
        int min = 0;
        int max = celebrationMessages.length - 1;
        int randomNumber = random.nextInt((max - min) + 1) + min;
        String celebration = celebrationMessages[randomNumber];
        return celebration;
    }



}




