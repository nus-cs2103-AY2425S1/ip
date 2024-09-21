package shnoop.ui;

import java.util.Scanner;

import shnoop.tasks.Task;

/**
 * Manages the interface and all visual elements that the user will see.
 */
public class Ui {
    private Scanner scanner;
    /**
     * Represents the quote bank to select quotes from.
     */
    private String[] quotes = new String[] { "You're unforgettable.", "Coded, tanned, fit and ready.",
        "You're undeniable."
    };

    /**
     * Initializes the UI and creates a scanner object for future use.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }



    /**
     * Prints out introductory speech at start of application.
     */
    public String showWelcome() {
        return ("\n ... Greetings loved one ʚ♡ɞ Let's take a journey ... "
                + "\n\n\n ✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-"
                + "✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿ \n"
                + " ✿ It's Shnoop, my dawg. I'm all up on ya. Whatchu need? ✿ \n"
                + " ✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿-✿ \n");
    }

    /**
     * Prints out a loading error to inform the user.
     */
    public String showLoadingError() {
        return (" ✿ Shnoop is currently experiencing some difficulties, let's have a fresh start. ✿ \n");
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
    public String goodbye() {
        return ("\n✿ Shnoop ✿: I'll check ya later, cause you represent. Don't worry we got it on lock. ♡");
    }

    /**
     * Informs the user of a specific error by printing it.
     *
     * @param string Error message to be printed.
     */
    public String showError(String string) {
        return ("✿ Shnoop ✿: My homie, listen when I say: " + string);
    }

    /**
     * Prints out divider between user input and Shnoop output.
     */
    public String showLine() {
        return ("\n✿-✿-✿-✿-✿-✿-✿-✿-✿ Say what you gotta say ✿-✿-✿-✿-✿-✿-✿-✿-✿");
    }

    /**
     * Informs the user and prints out the successful (or unsuccessful) marking of a task.
     *
     * @param b Boolean value indicating if task was successfully marked.
     * @param task Specific task that user tried to mark.
     */
    public String mark(boolean b, Task task) {
        String temp = "";
        if (b) {
            temp += ("✿ Shnoop ✿: Warm, wet and wild! I've marked this task as done: \n");
        } else {
            temp += ("✿ Shnoop ✿: Daisy dukes! This task was already done my love: \n");
        }
        return (temp + task);
    }

    /**
     * Informs the user and prints out the successful (or unsuccessful) unmarking of a task.
     *
     * @param b Boolean value indicating if task was successfully unmarked.
     * @param task Specific task that user tried to unmark.
     */
    public String unmark(boolean b, Task task) {
        String temp = "";
        if (b) {
            temp += ("✿ Shnoop ✿: Melted this popsicle! I've unmarked this task as done: \n");
        } else {
            temp += ("✿ Shnoop ✿: Daisy dukes! This task was never done my love: \n");
        }
        return (temp + task);
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
    public String addTask(Task task, int size) {
        String temp = "";
        temp += ("✿ Shnoop ✿: "
                + getRandomQuote(size % 3) + " I'll add that in for ya. \nTask Added: " + task);
        temp += ("\n✿ Shnoop ✿: You've got " + size + " doggy-dogs on the stereo.");
        return temp;
    }

    /**
     * Prints out a specific message everytime the list command is called.
     *
     * @param string String to be printed after the list command (typically reserved for task list).
     */
    public String list(String string) {
        String temp = "";
        temp += ("✿ Shnoop ✿: Find, fresh, fierce and ready.\n");
        temp += (string);
        return temp;
    }

    /**
     * Prints out a message that informs user of task deleted.
     *
     * @param task Task that user deleted.
     */
    public String delete(Task task) {
        return ("✿ Shnoop ✿: I know a place, where the grass is really greener. "
                + "I'll send this task there\n" + "Goodbye " + task + "!");
    }

    /**
     * Prints out a list of tasks that contain a given keyword.
     *
     * @param foundTasks Input contains String obtained from TaskList.
     */
    public String find(String foundTasks) {
        String temp = "";
        if (foundTasks.isEmpty()) {
            temp += ("✿ Shnoop ✿: I have travelled the world, and nothing comes close "
                    + "to the words you speak of.");
        } else {
            temp += ("✿ Shnoop ✿: My California gorls have found whatchu been looking for:\n");
            temp += (foundTasks);
        }
        return temp;
    }
}
