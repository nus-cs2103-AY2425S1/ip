package bob.command;

import java.time.LocalDate;

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
     * Constructor to initialise DeadlineCommand
     *
     * @param input Full command given by user.
     */
    public DeadlineCommand(String input) {
        super(input);
    }

    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        String input = getInput();
        try {
            Task newDeadlineTask = getDeadlineTask(input);
            taskList.updateWithNewTask(newDeadlineTask);
            storage.saveTaskListToStorage(taskList);
            Ui.showAddedTaskConfirmation(taskList);
            String deadlineTaskAddedConfirmationMessage = taskList.getAddedTaskString();
            return deadlineTaskAddedConfirmationMessage;
        } catch (InvalidTaskException e) {
            System.err.println(e.getMessage());
            return e.getMessage();
        }
    }

    private static Task getDeadlineTask(String input) throws InvalidTaskException {
        String deadlineDescription = Parser.parseDescriptionFromInput(input);
        LocalDate deadlineDueDate = Parser.parseDateFromInput(input);
        Task newTask = new Deadline(deadlineDescription, deadlineDueDate);
        return newTask;
    }
}
