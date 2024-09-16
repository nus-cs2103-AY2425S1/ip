package nebula.command;

import nebula.storage.Storage;
import nebula.task.Deadline;
import nebula.task.Task;
import nebula.task.TaskList;
import nebula.ui.Parser;
import nebula.ui.Ui;

import java.io.IOException;

public class AddDeadlineCommand extends Command {

    /**
     * Constructs an {@code AddDeadlineCommand} object with the specified description.
     *
     * @param description The command description containing details about the deadline task to be added.
     */
    public AddDeadlineCommand(String description) {
        super(description);
    }

    /**
     * Executes the add deadline command.
     * Parses the task description and deadline from the command, creates a new deadline task,
     * and adds it to the task list.
     *
     * @param tasks   The task list to which the new deadline task will be added.
     * @param ui      The UI component to display the result of adding the deadline task.
     * @param storage The storage component (not used in this method).
     * @throws IOException If an I/O error occurs during any file operations (e.g., saving the task list).
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        String command = getDescription();

        String taskInformation = Parser.splitCommandAndTaskDescription(command);
        String taskDescriptionDeadline = Parser.splitDeadlineCommand(taskInformation)[0];
        String taskDeadline = Parser.splitDeadlineCommand(taskInformation)[1];

        Task newDeadline = new Deadline(taskDescriptionDeadline, taskDeadline);
        String addedDeadline = tasks.addTask(newDeadline);

        return addedDeadline;
    }

    /**
     * Indicates whether this command will exit the application.
     *
     * @return false since adding a Deadline Task does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
