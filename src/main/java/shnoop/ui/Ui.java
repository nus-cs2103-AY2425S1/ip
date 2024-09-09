package shnoop.ui;

import shnoop.tasks.*;
import java.util.Scanner;

/**
 * Manages the interface and all visual elements that the user will see.
 */
public class Ui {
    Scanner scanner;

    /**
     * Initializes the UI and creates a scanner object for future use.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Represents the quote bank to select quotes from.
     */
    private String[] quotes = new String[] {
        "You're unforgettable.",
                "Coded, tanned, fit and ready.",
                "You're undeniable."
    };

    /**
     * Prints out introductory speech at start of application.
     */
    public void showWelcome() {
        System.out.println("\n ... Greetings loved one ʚ♡ɞ Let's take a journey ... \n\n\n ✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-"
                + "✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿ \n"
                + " ✿ It's Shnoop, my dawg. I'm all up on ya. Whatchu need? ✿ \n"
                + " ✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿ \n");
    }

    /**
     * Prints out a loading error to inform the user.
     */
    public void showLoadingError() {
        System.out.println(" ✿ Shnoop is currently experiencing some difficulties, let's have a fresh start. ✿ \n");
    }

    /**
     * Reads the next line in a file that should have been phrased as a command.
     *
     * @return String form of the command for future parsing.
     */
    public String readCommand() {
        String input = scanner.nextLine();
        return input;
    }

    /**
     * Prints out the exit message when closing the bot.
     */
    public void goodbye() {
        System.out.println("\n✿ Shnoop ✿: I'll check ya later, cause you represent. Don't worry we got it on lock. ♡");
    }

    /**
     * Informs the user of a specific error by printing it.
     *
     * @param string Error message to be printed.
     */
    public void showError(String string) {
        System.out.println("✿ Shnoop ✿: My homie, listen when I say: " + string);
    }

    /**
     * Prints out divider between user input and Shnoop output.
     */
    public void showLine() {
        System.out.println("\n✿-✿-✿-✿-✿-✿-✿-✿-✿ Say what you gotta say ✿-✿-✿-✿-✿-✿-✿-✿-✿");
    }

    /**
     * Informs the user and prints out the successful (or unsuccessful) marking of a task.
     *
     * @param b Boolean value indicating if task was successfully marked.
     * @param task Specific task that user tried to mark.
     */
    public void mark(boolean b, Task task) {
        if (b) {
            System.out.println("✿ Shnoop ✿: Warm, wet and wild! I've marked this task as done: ");
        } else {
            System.out.println("✿ Shnoop ✿: Daisy dukes! This task was already done my love: ");
        }
        System.out.println(task);
    }

    /**
     * Informs the user and prints out the successful (or unsuccessful) unmarking of a task.
     *
     * @param b Boolean value indicating if task was successfully unmarked.
     * @param task Specific task that user tried to unmark.
     */
    public void unmark(boolean b, Task task) {
        if (b) {
            System.out.println("✿ Shnoop ✿: Melted this popsicle! I've unmarked this task as done: ");
        } else {
            System.out.println("✿ Shnoop ✿: Daisy dukes! This task was never done my love: ");
        }
        System.out.println(task);
    }

    /**
     * Returns a quote from the quote bank.
     *
     * @param idx Should be a changing number.
     * @return Quote from quote bank.
     */
    public String getRandomQuote(int idx) {
        return quotes[idx];
    }

    /**
     * Prints out a message that informs user of task added, as well as number of tasks in the task list.
     *
     * @param task Task that was added.
     * @param size Current number of tasks in task list.
     */
    public void addTask(Task task, int size) {

        System.out.println("✿ Shnoop ✿: "
                + getRandomQuote(size % 3) + " I'll add that in for ya. \nTask Added: " + task);
        System.out.println("✿ Shnoop ✿: You've got " + size + " doggy-dogs on the stereo.");
    }

    /**
     * Prints out a specific message everytime the list command is called.
     *
     * @param string String to be printed after the list command (typically reserved for task list).
     */
    public void list(String string) {
        System.out.println("✿ Shnoop ✿: Find, fresh, fierce and ready.");
        System.out.println(string);
    }

    /**
     * Prints out a message that informs user of task deleted.
     *
     * @param task Task that user deleted.
     */
    public void delete(Task task) {
        System.out.println("✿ Shnoop ✿: I know a place, where the grass is really greener. "
                + "I'll send this task there\n" + "Goodbye " + task + "!");
    }

    /**
     * Prints out a list of tasks that contain a given keyword.
     *
     * @param foundTasks Input contains String obtained from TaskList.
     */
    public void find(String foundTasks) {
        if (foundTasks.isEmpty()) {
            System.out.println("✿ Shnoop ✿: I have travelled the world, and nothing comes close "
            + "to the words you speak of.");
        } else {
            System.out.println("✿ Shnoop ✿: My California gorls have found whatchu been looking for:");
            System.out.println(foundTasks);
        }
    }
}
