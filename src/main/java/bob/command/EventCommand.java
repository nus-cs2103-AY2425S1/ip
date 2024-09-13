package bob.command;

import java.util.Arrays;

import bob.exception.InvalidTaskException;
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
        String input = this.getInput();
        String[] inputArray = input.split("\s+");
        try {
            //add task to temp tasklist
            if (inputArray.length < 7) {
                throw new InvalidTaskException("Event instruction is too short. "
                        + "Should be '<description> /from <day> <start_time> /to <end_time>'");
            }
            if (!inputArray[Arrays.asList(inputArray).indexOf("/from") + 3].equals("/to")) {
                throw new InvalidTaskException("Invalid use of event format. "
                        + "Should be 'event <description> /from <day> <start_time> /to <end_time>'");
            }
            //Get the parameters for creating a new Task.
            String taskDescription = taskList.getDescriptionOnly(input);
            if (taskDescription.equals("")) {
                throw new InvalidTaskException("OOPS!!! The description of a event cannot be empty.");
            }
            String day = inputArray[Arrays.asList(inputArray).indexOf("/from") + 1];
            String startTime = inputArray[Arrays.asList(inputArray).indexOf("/from") + 2];
            String endTime = inputArray[Arrays.asList(inputArray).indexOf("/to") + 1];
            Task newTask = new Event(taskDescription, day, startTime, endTime);
            //Update taskList instance by adding the new task.
            taskList.getAllRecords().add(taskList.getRecordSize(), newTask);
            taskList.incrementLatestRecordedIndex();
            //save task
            storage.saveRecordsToStorage(taskList.getAllRecords());
            //print confirmation
            String eventCommandString = taskList.getAddedTaskString();
            //return string representation of successful adding / unsuccessful adding.
            Ui.printLines(eventCommandString);
            return eventCommandString;
        } catch (InvalidTaskException e) {
            System.err.println(e.getMessage());
            return e.getMessage();
        }
    }
}
