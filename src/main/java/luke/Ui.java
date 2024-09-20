package luke;

import java.util.List;

import luke.command.Command;
import luke.env.Constants;
import luke.task.NoDescriptionException;
import luke.task.Task;
import luke.task.TaskList;
import luke.task.UnknownCommandException;

/**
 * Represents the user interface of the bot. This class is responsible for handling user input and processing
 * commands to read/write from/to the task list.
 * <p>
 * This class supports the following command keywords:
 * <ul>
 *     <li>{@code list} displays the task list</li>
 *     <li>{@code mark} marks tasks that are complete</li>
 *     <li>{@code unmark} unmarks tasks that are incomplete</li>
 *     <li>{@code find} returns a filtered version of the task list based on search criteria</li>
 *     <li>{@code delete} deletes items from a list</li>
 * </ul>
 *
 * @see TaskList
 * @see Command
 */
public class Ui {
    private static TaskList taskList = new TaskList();

    /**
     * Takes in user input and passes it to handleCommand.
     * @return The message to be printed on the UI that corresponds with the
     */
    public static String handleUserInput(String input) {
        Command command = Parser.parseInputData(input);
        return handleCommand(command, false);
    }

    /**
     * Determines which procs to invoke depending on the command word.
     * @param command command word and its associated args
     * @param isLoadingFromDisk true if the command was loaded from save data
     * @return command output
     */
    public static String handleCommand(Command command, boolean isLoadingFromDisk) {
        try {
            switch (command.getCommand()) {
            case "bye" -> {
                return Constants.BYE_MESSAGE;
            }
            case "list" -> {
                return showList();
            }
            case "mark", "unmark" -> {
                return handleTaskMarking(command);
            }
            case "delete" -> {
                return handleDelete(command);
            }
            case "find" -> {
                return handleFind(command);
            }
            default -> {
                return handleAddTask(command, isLoadingFromDisk);
            }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return Constants.BAD_COMMAND;
        }
    }

    public static String showList() {
        return "here's everything that's in your list:\n" + taskList.printList();
    }

    /**
     * Marks or unmarks tasks.
     * @param command The mark/unmark command word and its associated tasks
     */
    public static String handleTaskMarking(Command command) {
        // assert command.getArgs() != null;
        int taskToMark = Integer.parseInt(command.getArgs());
        try {
            Task task = taskList.getTask(taskToMark - 1);
            task.changeMark();
            Storage.saveData(taskList.getTaskList());
            return (command.getCommand().equals("mark")
                    ? "ok i've marked"
                    : "ok i've unmarked")
                    + " this task:\n"
                    + Constants.INDENT + task.taskDescription();

        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return TaskList.taskNotFound(taskToMark);
        }
    }

    /**
     * Deletes a task from the task list.
     * @param command The "delete" command word and its arg -- the index of the relevant task in the task list
     */
    public static String handleDelete(Command command) {
        int taskToDelete = Integer.parseInt(command.getArgs());
        try {
            Task deletedTask = taskList.removeTask(taskToDelete - 1);
            Storage.saveData(taskList.getTaskList());
            return "alright i've purged this task for you:\n"
                    + Constants.INDENT + deletedTask.taskDescription() + "\n"
                    + taskList.listSizeUpdateMessage();
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return TaskList.taskNotFound(taskToDelete);
        }
    }

    /**
     * Processes a 'find' command by filtering a taskList for tasks that match the provided search criteria.
     * The tasks are filtered based on the arguments of the {@link Command} and returned
     * as a formatted string that lists all matching tasks as a numbered list.
     * <p>
     * The method uses the {@link TaskList#findTasks(String)} to retrieve a list of tasks
     * that match the search criteria, then formats them into a numbered list.
     *
     * @param command The {@link Command} object containing the search criteria in its arguments.
     *                The command's arguments are used to filter tasks from the task list.
     * @return A formatted string containing the matching tasks.
     */
    public static String handleFind(Command command) {
        String filteredList = "";
        List<Task> matchingTasks = taskList.findTasks(command.getArgs());
        for (int i = 0; i < matchingTasks.size(); i++) {
            filteredList = filteredList + (i + 1) + ". " + matchingTasks.get(i).taskDescription() + "\n";
        }
        return "here are the matching tasks in your list:\n" + filteredList;
    }

    /**
     * Adds a task to the task list and catches possible errors stemming from this addition.
     * @param command Command word and its associated args
     * @param isLoadingFromDisk {@code true} if the command was loaded from save data
     */
    public static String handleAddTask(Command command, boolean isLoadingFromDisk) {
        try {
            return taskList.addToList(command.getCommand(),
                    command.getArgs(),
                    command.getMark().equalsIgnoreCase("x"),
                    isLoadingFromDisk);
        } catch (NoDescriptionException e) {
            return "hmm...a description seems to be missing. try again?";
        } catch (UnknownCommandException e) {
            return "hmm... i don't quite recognise that command. try again?";
        }
    }
}
