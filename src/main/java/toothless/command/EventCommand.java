package toothless.command;

import toothless.exceptions.NoDescriptionExceptions;
import toothless.exceptions.NoTimelineExceptions;
import toothless.exceptions.ToothlessExceptions;
import toothless.storage.Storage;
import toothless.task.Event;
import toothless.task.TaskList;
import toothless.ui.Ui;

/**
 * Represents a command to add an event task.
 */
public class EventCommand extends Command {

    private String description;

    /**
     * Constructor for EventCommand.
     *
     * @param description Description of the event task.
     *                    Should contain the description, start time and end time of the event.
     */
    public EventCommand(String description) {
        this.description = description;
    }

    @Override
    public String executeCommand(TaskList taskList, Ui ui, Storage storage) throws ToothlessExceptions {
        if (description.isEmpty()) {
            throw new NoDescriptionExceptions("event", "event <description> /from <start time> /to <end time>");
        } else if (!description.contains("/from") || !description.contains("/to")) {
            throw new NoTimelineExceptions("event", "event <description> /from <start time> /to <end time>");
        }
        String[] splitEvent = description.split("/from");
        if (splitEvent.length != 2) {
            throw new NoTimelineExceptions("event", "event <description> /from <start time> /to <end time>");
        }
        String[] splitEventTime = splitEvent[1].split("/to");
        if (splitEventTime.length != 2) {
            throw new NoTimelineExceptions("event", "event <description> /from <start time> /to <end time>");
        }
        String response = taskList.addTask(new Event(splitEvent[0], splitEventTime[0], splitEventTime[1]), ui, taskList);
        storage.saveTask(taskList.getList());
        return response;
    }
}
