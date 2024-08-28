package talkie.command;

import talkie.components.Storage;
import talkie.components.Ui;
import talkie.exception.TalkieMissingArgumentException;
import talkie.task.Event;
import talkie.task.Task;
import talkie.task.TaskList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class EventCommand extends Command {

    private String fullCommand;

    public EventCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws TalkieMissingArgumentException {
        String[] parts = fullCommand.split(" ", 2); // Split into type and the rest of the input

        try {
            if (parts.length == 2) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                String details = parts[1]; // rest of the input (eg. from, to details)
                String[] eventParts = details.split("/from | /to ");

                String description = eventParts[0].trim();
                String from = eventParts[1].trim();
                String to = eventParts[2].trim();

                LocalDateTime startTime = LocalDateTime.parse(from, formatter);
                LocalDateTime endTime = LocalDateTime.parse(to, formatter);

                if (startTime.isAfter(endTime)) {
                    System.out.println("The end time must be after the start time!");
                    return;
                }

                Task newEvent = new Event(description, startTime, endTime);
                tasks.addTask(newEvent);
                ui.addMessage(newEvent, tasks.size());
            } else {
                throw new TalkieMissingArgumentException(parts[0],
                        "The 'description', 'from' and 'to' of event cannot be empty.");
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
