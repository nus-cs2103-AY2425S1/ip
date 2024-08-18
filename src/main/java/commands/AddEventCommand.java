package commands;

import common.Command;
import common.SkibidiException;
import common.Ui;
import storage.TaskStorage;
import storage.Event;

public class AddEventCommand extends Command {
    private String description;
    private String startTime;
    private String endTime;

    public AddEventCommand(String input) throws SkibidiException{
        String[] partsFrom = input.split("/from ");

        if (partsFrom.length < 2) {
            throw new SkibidiException("Invalid event format. " +
                    "Usage: event [description] /from [start time] /to [end time]");
        } else {
            String[] partsTo = partsFrom[1].split("/to ");
            if (partsTo.length < 2) {
                throw new SkibidiException("Invalid event format. " +
                        "Usage: event [description] /from [start time] /to [end time]");
            } else {
                this.description = partsFrom[0].substring(6).trim();
                this.startTime = partsTo[0].trim();
                this.endTime = partsTo[1].trim();
            }
        }
    }

    @Override
    public boolean execute(Ui ui, TaskStorage storage) throws SkibidiException {
        Event event = new Event(description, startTime, endTime);
        storage.addTask(event);
        ui.printMessage("Got it. I've added this task:\n  " + event);
        return true;
    }
}
