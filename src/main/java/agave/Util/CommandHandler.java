package agave.Util;

import agave.Task.Task;
import agave.Task.TaskList;


import java.io.IOException;
import java.util.ArrayList;

public class CommandHandler {

    /**
     * Handles user commands and returns the corresponding response.
     *
     * @param parser  The parser object containing the user input.
     * @param tasks   The task list to perform actions on.
     * @param ui      The UI object to display responses.
     * @return The response to the user's command.
     * @throws AgaveException If there is an error with the command.
     * @throws IOException    If there is an issue with saving or loading tasks.
     */
    public static String handleCommand(Parser parser, TaskList tasks, Ui ui) throws AgaveException, IOException {
        String response = "";
        String command = parser.getCommand();

        if (command.equals("bye")) {
            return ui.showBye();
        } else if (command.equals("list")) {
            return ui.showTasks(tasks.getTasks());
        } else if (command.equals("mark")) {
            tasks.markTask(parser.getTaskNumber());
            return ui.showMarkedTask(tasks.getTask(parser.getTaskNumber() - 1));
        } else if (command.equals("unmark")) {
            tasks.unmarkTask(parser.getTaskNumber());
            return ui.showUnmarkedTask(tasks.getTask(parser.getTaskNumber() - 1));
        } else if (command.equals("todo")) {
            tasks.addTask(parser.parseTodo());
            return ui.showTaskAdded(tasks.getLastTask(), tasks.size(), tasks.getTasks());
        } else if (command.equals("deadline")) {
            tasks.addTask(parser.parseDeadline());
            return ui.showTaskAdded(tasks.getLastTask(), tasks.size(), tasks.getTasks());
        } else if (command.equals("event")) {
            tasks.addTask(parser.parseEvent());
            return ui.showTaskAdded(tasks.getLastTask(), tasks.size(), tasks.getTasks());
        } else if (command.equals("delete")) {
            tasks.deleteTask(parser.getTaskNumber());
            return "Task deleted!";
        } else if (command.equals("find")) {
            ArrayList<Task> result = tasks.findTasks(parser.getKey());
            return ui.searchResult(result);
        } else if(command.equals("sort")) {
            tasks.sortByDeadline();
            return ui.showSort();
        }
        else {
            throw new AgaveException("I'm sorry, but I don't understand the command: " + parser.getCommand());
        }
    }
}
