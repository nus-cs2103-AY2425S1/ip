package espresso.parser;

import espresso.command.InvalidCommandException;
import espresso.task.TaskList;
import espresso.task.TodoTask;
import espresso.task.DeadlineTask;
import espresso.task.EventTask;
import espresso.ui.Ui;
import java.text.ParseException;

/**
 * This class is responsible for parsing user inputs and executing the appropriate.
 * commands on the task list. The class interprets the input and is responsible for performing.
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
    public static String parse(String input, TaskList taskList, Ui ui) throws InvalidCommandException, ParseException {
        if (input.equals("list")) {
            return ui.printTasks(taskList);
        } else if (checkInput(input, "mark ")) {
            int i = Integer.parseInt(input.substring(5)) - 1;
            taskList.getTask(i).mark();
            return ui.printTaskMarked(taskList.getTask(i));
        } else if (checkInput(input, "unmark ")) {
            int i = Integer.parseInt(input.substring(7)) - 1;
            taskList.getTask(i).unmark();
            return ui.printTaskUnmarked(taskList.getTask(i));
        } else if (checkInput(input, "todo ")) {
            return ui.printTaskAdded(taskList.addTask(new TodoTask(input.substring(5))), "todo task");
        } else if (checkInput(input, "deadline ")) {
            String[] split = input.substring(9).split(" /by ");
            if (split.length != 2) {
                throw new InvalidCommandException("Invalid deadline format.");
            }
            return ui.printTaskAdded(taskList.addTaskWithAnomalyCheck(new DeadlineTask(split[0], split[1])), "deadline");
        } else if (checkInput(input, "event ")) {
            String[] split = input.substring(6).split(" /from | /to ");
            if (split.length != 3) {
                throw new InvalidCommandException("Invalid event format.");
            }
            return ui.printTaskAdded(taskList.addTaskWithAnomalyCheck(new EventTask(split[0], split[1], split[2])), "event");
        } else if (checkInput(input, "delete ")) {
            int i = Integer.parseInt(input.substring(7)) - 1;
            String res = ui.printTaskRemoved(taskList.getTask(i));
            taskList.removeTask(i);
            return res;
        } else if (checkInput(input, "find ")) {
            String[] split = input.split(" ");
            if (split.length != 2) {
                throw new InvalidCommandException("Invalid search term.");
            }
            String searchTerm = split[1];
            TaskList foundTasks = taskList.find(searchTerm);
            return ui.printFoundTasks(foundTasks);
        } else {
            return ui.printError("Command not found.");
        }
    }

    private static boolean checkInput(final String a, final String b) {
        return a.length() >= b.length() && a.substring(0, b.length()).equals(b);
    }
}
