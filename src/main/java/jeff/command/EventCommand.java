package jeff.command;

import jeff.Storage;
import jeff.TaskList;
import jeff.Ui;
import jeff.exceptions.JEFFException;
import jeff.task.Event;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class EventCommand extends Command {
    private String args;

    public EventCommand(String args) throws JEFFException {
        super();
        if (args.isEmpty() || !args.contains("/from") || !args.contains("/to")) {
            throw new JEFFException("You must provide a valid event task in the format: task /from date /to date!");
        }
        this.args = args;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JEFFException {
        String[] parts = args.split("/from | /to ", 3);
        if (parts.length < 3) {
            throw new JEFFException("You did not follow the format for providing an event!" +
                    "\nIt should be: task /from date /to date");
        }
        try {
            tasks.addTask(new Event(
                    parts[0].trim(),
                    LocalDateTime.parse(parts[1].trim(), Storage.DATE_TIME_FORMATTER),
                    LocalDateTime.parse(parts[2].trim(), Storage.DATE_TIME_FORMATTER)
            ));
            storage.saveTask(tasks.getTasks());
            ui.showMessage("added: " + tasks.getTask(tasks.size() - 1));
        } catch (DateTimeParseException e) {
            throw new JEFFException("You need to format your dates as follows: DD/MM/YYYY HHMM");
        }
    }
}