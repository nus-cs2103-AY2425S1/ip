package wenjiebot.commands;

import java.util.ArrayList;

import wenjiebot.Storage;
import wenjiebot.TaskList;
import wenjiebot.Ui;
import wenjiebot.exceptions.NoNumberInputtedException;
import wenjiebot.exceptions.OutOfBoundsException;
import wenjiebot.exceptions.WenJieException;
import wenjiebot.tasks.Task;

/**
 * Represents a command to snooze an existing task by changing its associated date and time.
 * The command can handle snoozing tasks of different types, including ToDo, Event, and Deadline tasks.
 */
public class SnoozeCommand extends Command {

    /**
     * Regex pattern for matching ToDo tasks.
     */
    private static String regexToDo = "todo .*";

    /**
     * Regex pattern for matching Deadline tasks.
     */
    private static String regexDeadline = "deadline .* /by .*";

    /**
     * Regex pattern for matching Event tasks.
     */
    private static String regexEvent = "event .* /.* /.*";
    /**
     * Constructs a {@code SnoozeCommand} with the specified exit status and input.
     *
     * @param isExit a boolean indicating whether the bot will exit after this command is executed.
     * @param input the input string representing the user's command.
     */
    public SnoozeCommand(boolean isExit, String input) {
        super(isExit, input);
    }

    /**
     * Executes the SnoozeCommand to update the date and time of a task in the task list.
     * If the task number is valid and within bounds, the date and time are updated, and
     * the user is informed that the task has been snoozed.
     *
     * @param tasks the list of tasks.
     * @param ui the user interface to display messages.
     * @param storage the storage handler for saving and loading tasks.
     * @throws WenJieException if there is an error with the command input, task bounds, or task type.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws WenJieException {
        String[] parts = getInput().split(" ");
        ArrayList<Task> taskList = tasks.getTasks();

        if (parts.length <= 1) {
            throw new NoNumberInputtedException();
        }

        int taskNo = Integer.parseInt(parts[1]) - 1;
        int descIndex = getInput().indexOf(parts[2]);
        String desc = getInput().substring(descIndex);

        if (taskNo + 1 > taskList.size()) {
            throw new OutOfBoundsException();
        }

        taskList.get(taskNo).setDateTime(desc);
        ui.showLine();
        System.out.println("Nyess master~~, I've snoozed this task:\n" + taskList.get(taskNo));
        ui.showLine();

        ui.setOutput("Yes master~~, I've snoozed this task:\n" + taskList.get(taskNo));
    }
}
