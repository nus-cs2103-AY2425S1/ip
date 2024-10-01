package flychat.command;

import flychat.core.Parser;
import flychat.core.TaskList;
import flychat.core.Ui;
import flychat.tasks.Event;

public class AddEventCommand extends Command {
    @Override
    public String execute(TaskList taskList, Ui ui, Parser parser, String inputString) {
        Event newEvent = Event.createNewEvent(parser.getTaskDescription(inputString),
                parser.getEventStartTime(inputString), parser.getEventEndTime(inputString), false);
        taskList.addToList(newEvent);
        return "Event added:\n  " + newEvent + "\nNow you have " + taskList.getSize()
                + " tasks in the list. HAVE FUN ^o^";
    }
}
