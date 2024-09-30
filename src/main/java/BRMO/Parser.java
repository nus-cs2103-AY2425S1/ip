package BRMO;

import java.text.ParseException;
import java.util.ArrayList;

import BRMO.task.Deadline;
import BRMO.task.Event;
import BRMO.task.TaskList;
import BRMO.task.Todo;

/**
 * The Parser class handles the interpretation of user commands.
 * It parses input commands, processes them, and modifies the task list accordingly.
 */
public class Parser {

    /**
     * Parses the user input command and executes the corresponding action.
     *
     * @param input    the input command from the user
     * @param taskList the TaskList to be modified based on the command
     * @param ui       the Ui to handle displaying output to the user
     * @throws InvalidCommandException if the user input is invalid or has incorrect formatting
     * @throws ParseException          if there is an error in parsing date-related commands
     */
    public static String parse(String input, TaskList taskList, Ui ui) throws InvalidCommandException, ParseException {
        assert input != null : "Input should not be null";
        assert taskList != null : "TaskList should not be null";
        assert ui != null : "Ui should not be null";

        if (input.equals("list")) {
            return ui.showTasks(taskList);
        }
        
        if (checkInput(input, "mark")) {
            int i = Integer.parseInt(input.split(" ")[1]) - 1;

            taskList.getTask(i).mark();
            return ui.showTaskMarked(taskList.getTask(i));
        }
        
        if (checkInput(input, "unmark")) {
            int i = Integer.parseInt(input.split(" ")[1]) - 1;

            taskList.getTask(i).unmark();
            return ui.showTaskUnmarked(taskList.getTask(i));
        }
        
        if (checkInput(input, "todo")) {
            return ui.showTaskAdded(taskList.addTask(
                new Todo(input.substring("todo".length() + 1))), "todo task");
        }
        
        if (checkInput(input, "deadline")) {
            String[] line = input.substring("deadline".length() + 1).split(" /by ");
            if (line.length != 2) {
                throw new InvalidCommandException("Invalid deadline format.");
            }

            return ui.showTaskAdded(taskList.addTask(new Deadline(line[0], line[1])), "deadline");
        }
        
        if (checkInput(input, "event")) {
            String[] line = input.substring("event".length() + 1).split(" /from | /to ");
            if (line.length != 3) {
                throw new InvalidCommandException("Invalid event format.");
            }

            return ui.showTaskAdded(taskList.addTask(new Event(line[0], line[1], line[2])), "event");
        }
        
        if (checkInput(input, "delete")) {
            int i = Integer.parseInt(input.split(" ")[1]) - 1;

            String res = ui.showTaskRemoved(taskList.getTask(i));
            taskList.removeTask(i);
            return res;
        }
        
        if (checkInput(input, "find")) {
            String[] line = input.split(" ");
            if (line.length != 2) {
                throw new InvalidCommandException("Invalid search term.");
            }
            String searchTerm = line[1];
            TaskList foundTasks = taskList.find(searchTerm);
            return ui.showFoundTasks(foundTasks);
        }

        return ui.showError("Command not found.");
    }
    /**
     * Checks whether the user input starts with a specific command.
     *
     * @param a the user input string
     * @param b the command to check
     * @return true if the input starts with the specified command, false otherwise
     */
    private static boolean checkInput(final String a, String b) {
        b += " ";
        return a.length() >= b.length() && a.substring(0, b.length()).equals(b);
    }
}
