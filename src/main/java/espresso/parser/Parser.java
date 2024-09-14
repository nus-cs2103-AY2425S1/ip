package espresso.parser;

import espresso.ui.Ui;
import espresso.task.TaskList;
import espresso.task.Task;
import espresso.task.TodoTask;
import espresso.task.DeadlineTask;
import espresso.task.EventTask;
import espresso.storage.Storage;
import espresso.parser.Parser;
import espresso.command.InvalidCommandException;
import java.text.ParseException;
import java.io.IOException;

/**
 * This class is responsible for parsing user inputs and executing the appropriate
 * commands on the task list. The class interprets the input and is responsible for performing
 * tasks such as adding and removing tasks as well as marking and unmarking tasks.
 */
public class Parser {

    /**
     * Parses the user's input and executes the corresponding actions
     *
     * @param input    The string input by user containing the command.
     * @param taskList The current task list where tasks are being stored.
     * @param ui       The UI object displayed to interact with the user.
     * @throws InvalidCommandException If the command format is invalid.
     * @throws ParseException          If there is an error in parsing task data.
     */
    public static void parse(String input, TaskList taskList, Ui ui) throws InvalidCommandException, ParseException {

        if (input.equals("list")) {
            ui.printTasks(taskList);
        } else if (checkInput(input, "mark ")) {
            int i = Integer.parseInt(input.substring(5)) - 1;

            taskList.getTask(i).mark();
            ui.printTaskMarked(taskList.getTask(i));
        } else if (checkInput(input, "unmark ")) {
            int i = Integer.parseInt(input.substring(7)) - 1;

            taskList.getTask(i).unmark();
            ui.printTaskUnmarked(taskList.getTask(i));
        } else if (checkInput(input, "todo ")) {
            ui.printTaskAdded(taskList.addTask(new TodoTask(input.substring(5))), "todo task");
        } else if (checkInput(input, "deadline ")) {
            String[] split = input.substring(9).split(" /by ");
            if (split.length != 2) {
                throw new InvalidCommandException("Invalid deadline format.");
            }

            ui.printTaskAdded(taskList.addTask(new DeadlineTask(split[0], split[1])), "deadline");
        } else if (checkInput(input, "event ")) {
            String[] split = input.substring(6).split(" /from | /to ");
            if (split.length != 3) {
                throw new InvalidCommandException("Invalid event format.");
            }

            ui.printTaskAdded(taskList.addTask(new EventTask(split[0], split[1], split[2])), "event");
        } else if (checkInput(input, "delete ")) {
            int i = Integer.parseInt(input.substring(7)) - 1;

            ui.printTaskRemoved(taskList.getTask(i));
            taskList.removeTask(i);
        } else {
            ui.printError("Command cannot be found");
        }
    }

    /**
     * Helper method to check if the input starts with the specific command prefix.
     *
     * @param a The full input string input by the user.
     * @param b The expected command prefix to check for.
     * @return True if the input starts with the prefix, false otherwise.
     */
    private static boolean checkInput(String a, String b) {
        return a.length() >= b.length() && a.substring(0, b.length()).equals(b);
    }
}