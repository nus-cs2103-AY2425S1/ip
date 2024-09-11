package duke;

import java.util.Scanner;

/**
 * The Class that handles the interaction between the user and the program.
 */
public class Ui {

    private static final String logo = " ,--.--------.    ,----.    ,-,--.             ,---.      \n"
                                      + "/==/,  -   , -\\,-.--` , \\ ,-.'-  _\\  _.-.    .--.'  \\     \n"
                                      + "\\==\\.-.  - ,-./==|-  _.-`/==/_ ,_.'.-,.'|    \\==\\-/\\ \\    \n"
                                      + " `--`\\==\\- \\  |==|   `.-.\\==\\  \\  |==|, |    /==/-|_\\ |   \n"
                                      + "      \\==\\_ \\/==/_ ,    / \\==\\ -\\ |==|- |    \\==\\,   - \\  \n"
                                      + "      |==|- ||==|    .-'  _\\==\\ ,\\|==|, |    /==/ -   ,|  \n"
                                      + "      |==|, ||==|_  ,`-._/==/\\/ _ |==|- `-._/==/-  /\\ - \\ \n"
                                      + "      /==/ -//==/ ,     /\\==\\ - , /==/ - , ,|==\\ _.\\=\\.-' \n"
                                      + "      `--`--``--`-----``  `--`---'`--`-----' `--`         \n"
                                      + "\n";

    private boolean flag = false;
    private Scanner scanner;

    /**
     * Initialises the Ui object as the constructor.
     *
     */
    public Ui() {
        setScanner(new Scanner(System.in));
    }

    /**
     * Setter method for the Ui class
     *
     * @param scanner Takes a Scanner object to be stored within the Ui for interactions with the user.
     *
     */
    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Displays a custom message to the user if there is an issue with loading the hard drive-stored tasks into the
     * program.
     *
     */
    public void showLoadingError() {
        System.out.println("Regrettably, the storage file you have indicated cannot be "
                           + "located within the depths of the system.");
    }

    /**
     * Displays a custom message to be displayed to the user at the start of the program.
     *
     */
    public void greet() {
        System.out.println(getGreet());

    }

    public String getGreet() {
        return "________________________________________________________\n"
                + "Hello! I'm\n" + logo
                + "How may I be of service to you in this moment?\n"
                + "________________________________________________________\n";

    }

    /**
     * Displays the tasks hitherto entered by the user.
     * @param tasks A TaskList object storing tasks entered so far.
     */
    public void printList(TaskList tasks) throws TaskListOutOfBoundsException {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
    }

    /**
     * This method returns the tasks hitherto entered by the user as a list
     * @param tasks The tasks listed by the user.
     * @return The tasks as an enumerated string.
     */
    public String stringList(TaskList tasks) throws TaskListOutOfBoundsException {
        String acc = "";
        for (int i = 0; i < tasks.size(); i++) {
            acc = acc + (i + 1) + "." + tasks.get(i) + "\n";
        }
        return acc;
    }

    /**
     * The method that displays the customised header when the user enters the appropriate command before it lists
     * the tasks.
     * @param tasks The Tasklist object holding tasks currently stored in the program.
     */
    public void display(TaskList tasks) throws TaskListOutOfBoundsException {
        System.out.println("Here are the tasks in your list:");
        printList(tasks);
    }
    public String guiDisplay(TaskList tasks) throws TaskListOutOfBoundsException {
        return "Here are the tasks in your list:\n" + stringList(tasks);
    }

    /**
     * The method that displays the customised header when the user searches for a specific description within the
     * tasks currently stored in the program before it lists the said tasks.
     * @param tasks The Tasklist object holding tasks currently stored in the program.
     */
    public void displaySearch(TaskList tasks) throws TaskListOutOfBoundsException {
        System.out.println("Here are the matching tasks in your list:");
        printList(tasks);
    }
    public String guiDisplaySearch(TaskList tasks) throws TaskListOutOfBoundsException {
        return "Here are the matching tasks in your list:\n" + stringList(tasks);
    }

    /**
     * The method that displays the customised header and footer, includng the task, when the user adds or deletes a
     * task.
     * @param task The task that was added or deleted.
     * @param addOrDelete The String holding the action (add or delete) done to the task.
     * @param tasks The Tasklist that holds or held the task of interest.
     */
    public void taskAddOrDeleteDisplay(Task task, String addOrDelete, TaskList tasks) {
        System.out.println("Got it. I've " + addOrDelete + "ed this task:");
        System.out.println(task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * The method that returns as a string the customised header and footer, includng the task, when the user adds or
     * deletes a task.
     * @param task The task that was added or deleted.
     * @param addOrDelete The String holding the action (add or delete) done to the task.
     * @param tasks The Tasklist that holds or held the task of interest.
     * @return A customised string showing what action (add or delete) was done to the task with respect to the
     *     TaskList tasks.
     */
    public String guiTaskAddOrDeleteDisplay(Task task, String addOrDelete, TaskList tasks) {
        return "Got it. I've " + addOrDelete + "ed this task:\n"
                + task + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.\n";
    }

    public String out() {
        return scanner.nextLine();
    }
}
