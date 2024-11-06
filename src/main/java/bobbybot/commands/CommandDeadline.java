package bobbybot.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import bobbybot.BobbyBotException;
import bobbybot.Storage;
import bobbybot.TaskList;
import bobbybot.tasks.Deadline;
import bobbybot.tasks.Task;
import bobbybot.ui.Ui;


/**
 * Represents a command to add a deadline task.
 */
public class CommandDeadline extends Command {

    private final String description;
    private final String by;


    /**
     * Creates a new CommandDeadline object.
     *
     * @param argument The argument string to create the deadline task.
     * @throws BobbyBotException If the argument is in the wrong format.
     */
    public CommandDeadline(String argument) throws BobbyBotException {
        Pattern r = Pattern.compile("(.*) /by (.*)");
        Matcher m = r.matcher(argument);
        if (m.find()) {
            description = m.group(1).trim();
            by = m.group(2).trim();
        } else {
            throw new BobbyBotException("Please specify it in this format 'deadline <description> /by <by>'.");
        }
        isUndoable = true;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BobbyBotException {
        memento = new Memento(tasks);

        Task deadline = new Deadline(description, by);
        tasks.addTask(deadline);

        ui.printAddTask(tasks, deadline);

        storage.saveTasksToFile(tasks.toArray());
    }
}
