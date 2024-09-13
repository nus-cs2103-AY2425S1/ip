package bob.command;

import java.time.LocalDate;
import java.util.Arrays;

import bob.exception.InvalidTaskException;
import bob.parser.Parser;
import bob.storage.Storage;
import bob.task.Deadline;
import bob.task.Task;
import bob.task.TaskList;
import bob.ui.Ui;

/**
 * ListCommand class executes list command.
 */
public class DeadlineCommand extends Command {

    /**
     * Constructor to initalise DeadlineCommand
     *
     * @param input
     */
    public DeadlineCommand(String input) {
        super(input);
    }

    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        String input = this.getInput();
        String[] inputArray = input.split("\s+");
        try {
            //add task to temp tasklist
            if (inputArray.length < 4) {
                throw new InvalidTaskException("Deadline instruction is too short. "
                        + "Should be 'deadline <description> /by <date>'");
            }

            String taskDescription = taskList.getDescriptionOnly(input);
            if (taskDescription == "") {
                throw new InvalidTaskException("Deadline task description cannot be empty");
            }

            //Get the parameters for creating a new Task.

            String dueDate = inputArray[Arrays.asList(inputArray).indexOf("/by") + 1];
            LocalDate deadline = Parser.parseDate(dueDate);
            if (deadline == null) {
                throw new InvalidTaskException("Invalid date format111: "
                        + dueDate
                        + "\nSupported date formats are: \n"
                        + "yyyy-MM-dd, dd-MM-yyyy, dd/MM/yyyy, MMM dd yyyy");
            }
            Task newTask = new Deadline(taskDescription, deadline);

            //Update taskList instance by adding the new task.
            taskList.getAllRecords().add(taskList.getRecordSize(), newTask);
            taskList.incrementLatestRecordedIndex();

            //save task
            storage.saveRecordsToStorage(taskList.getAllRecords());

            //print confirmation
            String deadlineCommandString = taskList.getAddedTaskString();
            //return string representation of successful adding / unsuccessful adding.
            Ui.printLines(deadlineCommandString);
            return deadlineCommandString;
        } catch (InvalidTaskException e) {
            System.err.println(e.getMessage());
            return e.getMessage();
        }
    }
}
