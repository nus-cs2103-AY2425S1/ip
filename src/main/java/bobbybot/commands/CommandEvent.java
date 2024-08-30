package bobbybot.commands;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import bobbybot.BobbyBotException;
import bobbybot.Event;
import bobbybot.Storage;
import bobbybot.Task;
import bobbybot.TaskList;
import bobbybot.ui.Ui;


/**
 * Represents a command to add an event task.
 */
public class CommandEvent extends Command {

    private final String description;
    private final String from;
    private final String to;


    /**
     * Creates a new CommandEvent object.
     *
     * @param argument The argument string to create the event task.
     * @throws BobbyBotException If the argument is in the wrong format.
     */
    public CommandEvent(String argument) throws BobbyBotException {
        Pattern r = Pattern.compile("(.*) /from (.*) /to (.*)");
        Matcher m = r.matcher(argument);
        if (m.find()) {
            description = m.group(1).trim();
            from = m.group(2).trim();
            to = m.group(3).trim();
        } else {
            throw new BobbyBotException("Please specify it in this format "
                    + "'event <description> /from <from> /to <to>.'");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BobbyBotException {
        Task event = new Event(description, from, to);
        tasks.addTask(event);
        ui.printAddTask(tasks, event);
        try {
            storage.saveTasksToFile(tasks.toArray());
        } catch (IOException e) {
            throw new BobbyBotException("Error saving to file.");
        }
    }
}
