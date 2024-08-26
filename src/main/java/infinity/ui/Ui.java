package infinity.ui;

import infinity.infinityexception.InfinityException;
import infinity.task.Task;
import infinity.tasklist.TaskList;

import java.util.Scanner;

/**
 * This class handles the user interface of the bot.
 */
public class Ui {

    private static final String BOT_NAME = "Infinity";
    private static final String BREAKLINE = 
            "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
    private Scanner userInputs = new Scanner(System.in);

    /**
     * Prepends the bot name to the input.
     * 
     * @param input The String input to prepend the bot name to.
     * @return The input with the bot name prepended.
     */
    public String prependBotName(String input) {
        return String.format("%s: %s", BOT_NAME, input);
    }

    /**
     * Prints the bot's response.
     * 
     * @param input The String input to print.
     */
    public void botSays(String input) {
        System.out.println(prependBotName(input));
        System.out.println(BREAKLINE);
    }

    /**
     * Prints the bot's response whilst being able to choose whether to print with a break line.
     * 
     * @param input The String input to print.
     * @param isBreakLine Whether to print a break line after the input.
     */
    public void botSays(String input, boolean isBreakLine) {
        System.out.println(prependBotName(input));
        if (isBreakLine) {
            System.out.println(BREAKLINE);
        }
    }
    
    /**
     * Lists the tasks in the task list.
     * 
     * @param tasks The task list to list.
     */
    public void listTasks(TaskList tasks) {
        if (tasks.isEmpty()) {
            System.out.println(prependBotName("Task list is empty :("));
        } else {
            System.out.println(prependBotName(""));

            int i = 1;
            for (Task task : tasks.getTasks()) {
                System.out.println(String.format("    %d. %s", i, task.toString()));
                i++;
            }
        }
        System.out.println(BREAKLINE);
    }

<<<<<<< HEAD
    /**
     * Tries to echoe the input back to the user but fails as input is not recognised.
     * 
     * @param input The input to attempt to echo back.
     * @throws InfinityException Always thrown as the input is not recognised.
     */
=======
    public void listTask(Task task, int i) {
        System.out.println(String.format("    %d. %s", i, task.toString()));
    }

>>>>>>> branch-Level-9
    public void echo(String input) throws InfinityException {
        throw new InfinityException("Wait a minute, that's not something I recognise...");
    }

    /**
     * Gets the next input from the user.
     * 
     * @return The next input from the user.
     */
    public String nextInput() {
        String currentInput = userInputs.nextLine();
        System.out.println(BREAKLINE);
        return currentInput;
    }

    /**
     * Ends the bot and program.
     */
    public void endBot() {
        botSays("Well, if you are leaving, then I must be infinitely too dumb :(");
        userInputs.close();
        System.exit(0);
    }

    /**
     * Creates a new Ui object.
     */
    public Ui() {
        System.out.println(BREAKLINE);
        botSays(String.format(
                "Hello, I'm a dummy bot called %s\n%s", BOT_NAME, "What can I not do for you?"));
    }
}