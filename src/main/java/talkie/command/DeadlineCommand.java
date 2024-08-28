package talkie.command;

import talkie.components.Storage;
import talkie.components.Ui;
import talkie.exception.TalkieMissingArgumentException;
import talkie.task.Deadline;
import talkie.task.Task;
import talkie.task.TaskList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DeadlineCommand extends Command {

    private String fullCommand;

    public DeadlineCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws TalkieMissingArgumentException {
        String[] parts = fullCommand.split(" ", 2); // Split into type and the rest of the input

        try {
            if (parts.length == 2) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

                String details = parts[1]; // rest of the input (eg. from, to details)
                String[] deadlineParts = details.split("/by ");
                String description = deadlineParts[0].trim();
                String by = deadlineParts[1].trim();

                LocalDateTime time = LocalDateTime.parse(by, formatter);

                Task newDeadline = new Deadline(description, time);
                tasks.addTask(newDeadline);
                ui.addMessage(newDeadline, tasks.size());
            } else {
                throw new TalkieMissingArgumentException(parts[0],
                        "The 'description' and 'by' of deadline cannot be empty.");
            }
        } catch (DateTimeParseException e) {
            ui.wrongDateTimeFormatMessage();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
