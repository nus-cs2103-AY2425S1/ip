package bobbybot.commands;

import bobbybot.*;
import bobbybot.ui.Ui;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandEvent extends Command{

    private final String description;
    private final String from;
    private final String to;


    public CommandEvent(String argument) throws DukeException{
        Pattern r = Pattern.compile("(.*) /from (.*) /to (.*)");
        Matcher m = r.matcher(argument);
        if (m.find()) {
            description = m.group(1).trim();
            from = m.group(2).trim();
            to = m.group(3).trim();
        } else {
            throw new DukeException("Please specify it in this format 'event <description> /from <from> /to <to>.'");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task event = new Event(description, from, to);
        tasks.addTask(event);
        ui.printAddTask(tasks, event);
        try {
            storage.saveTasksToFile(tasks.toArray());
        } catch (IOException e) {
            throw new DukeException("Error saving to file.");
        }
    }
}
