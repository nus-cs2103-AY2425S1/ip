package infinity.ui;

import infinity.infinityexception.InfinityException;
import infinity.task.Task;
import infinity.tasklist.TaskList;

import java.util.Scanner;

public class Ui {

    private static final String BOT_NAME = "Infinity";
    private static final String BREAKLINE = 
            "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
    private Scanner userInputs = new Scanner(System.in);

    public String prependBotName(String input) {
        return String.format("%s: %s", BOT_NAME, input);
    }

    public void botSays(String input) {
        System.out.println(prependBotName(input));
        System.out.println(BREAKLINE);
    }

    public void listTasks(TaskList tasks) {
        if (tasks.isEmpty()) {
            System.out.println(prependBotName("Task list is empty :("));
        } else {
            System.out.println(prependBotName(""));

            int i = 1;
            for (Task task : tasks.getTasks()) {
                System.err.println(String.format("    %d. %s", i, task.toString()));
                i++;
            }
        }
        System.out.println(BREAKLINE);
    }

    public void echo(String input) throws InfinityException {
        throw new InfinityException("Wait a minute, that's not something I recognise...");
    }

    public String nextInput() {
        String currentInput = userInputs.nextLine();
        System.out.println(BREAKLINE);
        return currentInput;
    }

    public void endBot() {
        botSays("Well, if you are leaving, then I must be infinitely too dumb :(");
        userInputs.close();
        System.exit(0);
    }

    public Ui() {
        System.out.println(BREAKLINE);
        botSays(String.format(
                "Hello, I'm a dummy bot called %s\n%s", BOT_NAME, "What can I not do for you?"));
    }
}