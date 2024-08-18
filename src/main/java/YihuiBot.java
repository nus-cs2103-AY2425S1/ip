import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import executable.Executable;
import executable.Greet;
import executable.TaskModifier;

import task.Task;

/**
 * Your friendly todolist bot.
 *
 * @author Toh Yi Hui A0259080A
 */
public class YihuiBot {
    // The name of this bot
    private static final String NAME = "YihuiBot";

    // List of tasks
    private static ArrayList<Task> tasks;

    public static void main(String[] args) {
        reset();

        greetings();

        Scanner userInput = new Scanner(System.in);
        Parser parser = new Parser();
        try {
            while (true) {
                String input = userInput.nextLine();

                // Returns an Executable based on what was parsed into the parser.
                Executable exec = parser.parse(input);

                if (exec instanceof TaskModifier) {
                    TaskModifier taskModifier = (TaskModifier) exec;
                    taskModifier.setTasks(tasks);
                }
                
                if (exec.executeAndPrint() > 0){
                    // The exit code of executing the Executable is > 0, and the program
                    // should be terminated.
                    break;
                }
            }
        } catch (IllegalStateException | NoSuchElementException | NullPointerException e) {
            System.out.println("An error occured. " + e.getMessage());
        }
    }

    private static void reset() {
        tasks = new ArrayList<>();
    }

    private static void greetings() {
        new Greet(NAME).executeAndPrint();
    }
}

