package bob.command;

import java.util.Arrays;

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
     * Constructor to initalise EventCommand
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
        String eventDescription = Parser.parseDescriptionFromInput(input);
        String[] inputArray = Parser.parseInputIntoStringArray(input);
        String day = inputArray[Arrays.asList(inputArray).indexOf("/from") + 1];
        String startTime = inputArray[Arrays.asList(inputArray).indexOf("/from") + 2];
        String endTime = inputArray[Arrays.asList(inputArray).indexOf("/to") + 1];
        Task newEventTask = new Event(eventDescription, day, startTime, endTime);
        return newEventTask;
    }
}
