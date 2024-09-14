package botty.commands;

import botty.exceptions.BottyException;
import botty.exceptions.EmptyArgumentException;
import botty.tasks.Event;
import botty.tasks.EventData;
import botty.tasks.TaskManager;

/**
 * Defines the behaviour of the Event command
 */
public class EventCommand extends AddTaskCommand {
    /**
     * Executes the event command, adding an event to the given task manager with
     * the given arguments
     * @param taskManager
     * @param parsedInput
     * @return success message
     * @throws BottyException if given input is invalid
     */
    @Override
    public String execute(TaskManager taskManager, ParsedInput parsedInput) throws BottyException {
        try {
            parsedInput.changeMainFlag("desc");
            Event event = new Event(new EventData(parsedInput));
            return addToTaskList(taskManager, event);
        } catch (EmptyArgumentException ex) {
            throw new BottyException("I am unable to add that event! Please provide details in "
                    + "the following format: [description] /from [start] /to [end]");
        }
    }
    /**
     * Returns true as the command will edit the task list
     */
    @Override
    public boolean changesTaskList() {
        return true;
    }
}
