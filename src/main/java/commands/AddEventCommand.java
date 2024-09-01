package commands;

import skibidi.Command;
import skibidi.SkibidiException;
import skibidi.Ui;
import storage.TaskStorage;
import storage.Event;

import java.io.IOException;
import java.time.format.DateTimeParseException;

public class AddEventCommand extends Command {
    private String description;
    private String startTime;
    private String endTime;

    public AddEventCommand(String input) throws SkibidiException{
        String[] partsFrom = input.split("/from ");

        if (partsFrom.length < 2) {
            throw new SkibidiException("Invalid event format. " +
                    "Usage: event [description] /from [start time] /to [end time]");
        }

        String[] partsTo = partsFrom[1].split("/to ");
        if (partsTo.length < 2) {
            throw new SkibidiException("Invalid event format. " +
                    "Usage: event [description] /from [start time] /to [end time]");
        }

        this.description = partsFrom[0].substring(6).trim();
        this.startTime = parseDateTime(partsTo[0].trim());
        this.endTime = parseDateTime(partsTo[1].trim());



    }

    private String parseDateTime(String dateTimeStr) throws SkibidiException {
        String[] dateTimeParts = dateTimeStr.split(" ", 2);
        String date = dateTimeParts[0];
        String time = dateTimeParts.length > 1 ? dateTimeParts[1] : "00:00";  // Default to "00:00" if time is missing

        return date + "T" + time;
    }

    @Override
    public boolean execute(Ui ui, TaskStorage storage) {
        try {
            Event event = new Event(description, startTime, endTime, false);
            storage.addTask(event);
            ui.printMessage("Got it. I've added this task:\n  " + event);
        } catch (SkibidiException | IOException e) {
            ui.printMessage(e.getMessage());
        } catch (DateTimeParseException e) {
            ui.printMessage("Invalid date format. Please use yyyy-mm-dd hh:mm.");
        }
        return true;
    }
}
