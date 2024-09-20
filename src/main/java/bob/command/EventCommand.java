package bob.command;

import bob.exception.InvalidTaskException;
import bob.parser.Parser;
import bob.storage.Storage;
import bob.task.Event;
import bob.task.Task;
import bob.task.TaskList;
import bob.ui.Ui;

/**
 * ListCommand class executes list command.
 */
public class EventCommand extends Command {

    /**
     * Constructor to initialise EventCommand
     *
     * @param input
     */
    public EventCommand(String input) {
        super(input);
    }

    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        String input = getInput();
        try {
            Task newEventTask = getEventTask(input);
            taskList.updateWithNewTask(newEventTask);
            storage.saveTaskListToStorage(taskList);
            Ui.showAddedTaskConfirmation(taskList);
            String eventTaskAddedConfirmationMessage = taskList.getAddedTaskString();
            return eventTaskAddedConfirmationMessage;
        } catch (InvalidTaskException e) {
            System.err.println(e.getMessage());
            return e.getMessage();
        }
    }

    private static Task getEventTask(String input) throws InvalidTaskException {
        String[] eventDetailArray = Parser.parseEvent(input);
        String eventDescription = eventDetailArray[0];
        String day = eventDetailArray[1];
        String startTime = eventDetailArray[2];
        String endTime = eventDetailArray[3];
        Task newEventTask = new Event(eventDescription, day, startTime, endTime);
        return newEventTask;
    }
}
