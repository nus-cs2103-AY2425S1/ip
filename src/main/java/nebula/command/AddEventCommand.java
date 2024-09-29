package nebula.command;

import nebula.storage.Storage;
import nebula.task.Event;
import nebula.task.Task;
import nebula.task.TaskList;
import nebula.ui.Parser;
import nebula.ui.Ui;

import java.io.IOException;

public class AddEventCommand extends Command {

    /**
     * Constructs an AddEventCommand object with the specified description.
     *
     * @param description The command description containing details about the event task to be added.
     */
    public AddEventCommand(String description) {
        super(description);
    }

    /**
     * Executes the add event command.
     * Parses the task description, start time, and end time from the command, creates a new event task,
     * and adds it to the task list.
     *
     * @param tasks   The task list to which the new event task will be added.
     * @param ui      The UI component to display the result of adding the event task.
     * @param storage The storage component (not used in this method).
     * @throws IOException If an I/O error occurs during any file operations (e.g., saving the task list).
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        String command = getDescription();

        String taskInfo = Parser.splitCommandAndTaskDescription(command);
        String taskDescriptionEvent = Parser.splitEventCommand(taskInfo)[0];
        String startInfo = Parser.splitEventCommand(taskInfo)[1];
        String endInfo = Parser.splitEventCommand(taskInfo)[2];

        String taskStart = Parser.splitCommandAndTaskDescription(startInfo);
        String taskEnd = Parser.splitCommandAndTaskDescription(endInfo);

        Task newEvent = new Event(taskDescriptionEvent, taskStart, taskEnd);
        String addedEvent = tasks.addTask(newEvent);

        return addedEvent;
    }

    /**
     * Indicates whether this command will exit the application.
     *
     * @return false since adding an Event Task does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
