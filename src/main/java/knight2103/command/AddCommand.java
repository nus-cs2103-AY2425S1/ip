package knight2103.command;

import knight2103.Ui;
import knight2103.files.Storage;
import knight2103.tasks.TaskList;
import knight2103.tasks.Task;
import knight2103.tasks.TodoTask;
import knight2103.tasks.DeadlineTask;
import knight2103.tasks.EventTask;

import java.io.IOException;
import java.time.format.DateTimeParseException;

public class AddCommand extends Command {
    AddCommand(CommandVerb verb, String description) {
        super(verb, description);
    }

    /**
     * Executes the AddCommand which create the task(s) based on user input and
     * add them to the list of tasks. Three different types of task can be created which
     * correspond to their respective AddCommand: Todo, Deadline, Event.
     *
     * @param tasks The class storing the list of tasks found in the bot.
     * @param ui The user interface of the bot.
     * @param storage The class containing the file that saves the list of tasks.
     * @throws DateTimeParseException If the deadline in Deadline() is not written in
     * yyyy-MM-dd format or the start and end time written in Event() is not written in
     * yyyy-MM-ddThh:mm format.
     * @throws ArrayIndexOutOfBoundsException If the command is in inappropriate format.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task taskToAdd;
        final String DEADLINE_DELIMITER = " /by ";
        final String EVENT_DELIMITER_1 = " /from ";
        final String EVENT_DELIMITER_2 = " /to " ;
        final int DESCRIPTION_INDEX = 0;
        final int DEADLINE_INDEX = 1;
        final int START_TIME_INDEX = 1;
        final int END_TIME_INDEX = 2;

        try {
            if (this.verb == CommandVerb.TODO) {
                taskToAdd = new TodoTask(this.predicate);
            } else if (this.verb == CommandVerb.DEADLINE) {
                String[] deadlineArray = this.predicate.split(DEADLINE_DELIMITER);
                taskToAdd = new DeadlineTask(
                        deadlineArray[DESCRIPTION_INDEX],
                        deadlineArray[DEADLINE_INDEX]);
            } else { // CommandVerb.EVENT
                String[] eventArray = this.predicate.split(EVENT_DELIMITER_1 + "|" + EVENT_DELIMITER_2);
                taskToAdd = new EventTask(
                        eventArray[DESCRIPTION_INDEX],
                        eventArray[START_TIME_INDEX],
                        eventArray[END_TIME_INDEX]);
            }
            tasks.add(taskToAdd);
            storage.saveToFile(taskToAdd);
            return ui.showAdd(taskToAdd, tasks);
        } catch (DateTimeParseException e) {
            return "Failed to execute Command:\nInput of Date or time format is wrong."
                    + "\nFor Deadline task, the format should be in yyyy-MM-dd"
                    + "\nFor Event task, the format should be in yyyy-MM-ddThh:mm";
        } catch (ArrayIndexOutOfBoundsException e) {
            return "Failed to execute Command:\nIncorrect Command input."
                    + String.format("\nFor Deadline task,%sbefore deadline", DEADLINE_DELIMITER)
                    + String.format("\nFor Event task,%sand%smust be present", EVENT_DELIMITER_1, EVENT_DELIMITER_2);
        } catch (IOException e) { // from save() in Storage class
            return "Failed to execute Command:\nProblems creating an instance of FileWriter";
        }
    }
}
