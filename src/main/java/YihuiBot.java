import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import executable.Executable;
import executable.Greet;
import executable.TaskModifier;

import task.Task;

import exception.BotException;

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
                    exec = taskModifier;
                }

                int exitCode = exec.execute();

                prettyPrint(exec.getOutput());

                if (exitCode > 0){
                    break;
                }
            }
        } catch (IllegalStateException | NoSuchElementException | NullPointerException e) {
            prettyPrint("An error occured. " + e.getMessage());
        } finally {
            userInput.close();
        }
    }

    private static void reset() {
        tasks = new ArrayList<>();
    }

    private static void greetings() {
        Greet greet = new Greet(NAME);
        greet.execute();
        prettyPrint(greet.getOutput());
    }

    private static void prettyPrint(String s) {
        String wrapped = wrapStringWithHorizontalLines(s);
        System.out.println(wrapped);
    }

    private static String wrapStringWithHorizontalLines(String s) {
        String horizontalLine = "-----------------------------------------------------------------";
        return horizontalLine + "\n" + s + "\n" + horizontalLine;
    }
}

