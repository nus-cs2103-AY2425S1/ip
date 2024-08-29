package jeff.command;

import jeff.Storage;
import jeff.TaskList;
import jeff.Ui;
import jeff.exceptions.JEFFException;
import jeff.task.Deadline;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class DeadlineCommand extends Command {
    private String args;

    public DeadlineCommand(String args) throws JEFFException {
        super();
        if (args.isEmpty() || !args.contains("/by")) {
            throw new JEFFException("You must provide a valid deadline task in the format: task /by date!");
        }
        this.args = args;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JEFFException {
        String[] parts = args.split("/by", 2);
        try {
            tasks.addTask(new Deadline(parts[0].trim(),
                    LocalDateTime.parse(parts[1].trim(), Storage.DATE_TIME_FORMATTER)));
            storage.saveTask(tasks.getTasks());
            ui.showMessage("added: " + tasks.getTask(tasks.size() - 1));
        } catch (DateTimeParseException e) {
            throw new JEFFException("You need to format your dates as follows: DD/MM/YYYY HHMM");
        }
    }
}