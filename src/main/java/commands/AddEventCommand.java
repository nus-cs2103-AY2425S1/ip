package commands;

import common.Command;
import common.Ui;
import storage.TaskStorage;
import storage.Event;

public class AddEventCommand extends Command {
    private String description;
    private String startTime;
    private String endTime;

    public AddEventCommand(String input) {
        String[] partsFrom = input.split("/from ");

        if (partsFrom.length < 2) {
            this.description = null;
            this.startTime = null;
            this.endTime = null;
        } else {
            String[] partsTo = partsFrom[1].split("/to ");
            if (partsTo.length < 2) {
                this.description = null;
                this.startTime = null;
                this.endTime = null;
            } else {
                this.description = partsFrom[0].substring(6).trim();
                this.startTime = partsTo[0].trim();
                this.endTime = partsTo[1].trim();
            }
        }
    }

    @Override
    public boolean execute(Ui ui, TaskStorage storage) {
        if (description == null || startTime == null || endTime == null) {
            ui.printMessage("Invalid event format. Usage: event [description] /from [start time] /to [end time]");
            return true;
        }

        Event event = new Event(description, startTime, endTime);
        storage.addTask(event);
        ui.printMessage("Got it. I've added this task:\n  " + event);
        return true;
    }
}
