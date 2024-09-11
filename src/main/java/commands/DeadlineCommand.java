package commands;

import java.time.DateTimeException;

import applemazer.Storage;
import applemazer.TaskList;
import applemazer.Ui;
import tasks.Deadline;
import tasks.DuplicateHandler;
import tasks.Task;

/**
 * Class that represents the "deadline" command.
 */
public class DeadlineCommand extends Command {
    private final String desc;
    private final String deadline;

    /**
     * Constructor for a {@code DeadLineCommand} object.
     * @param desc Description of the task.
     * @param deadline The deadline for the task, as a String.
     */
    public DeadlineCommand(String desc, String deadline) {
        this.desc = desc;
        this.deadline = deadline;
    }

    /**
     * Executes the "deadline" command which adds a {@code Deadline} task to the task list.
     *
     * @param tasks            The task list to use.
     * @param storage          The storage object containing the filepath which the chatbot saves to and loads from.
     * @param ui               The Ui object used to generate the string to print.
     * @param duplicateHandler The duplicate handler to use if necessary.
     * @return The string to print.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui, DuplicateHandler duplicateHandler) {
        try {
            Task task = new Deadline(desc, deadline);
            tasks.add(task);
            duplicateHandler.addNewDeadline(((Deadline) task).getKey());
            storage.saveTaskList();
            return ui.getTaskAddedMessage(task, tasks.size());
        } catch (DateTimeException e) {
            return """
                   OOPS!!! The description of deadline is wrong.
                   Try 'deadline <description> /by <yyyy-mm-dd> <HHmm>'
                         'deadline <description> /by <dd/MM/yyyy> <HHmm>'.
                   It is not necessary to input the time!
                   """;
        }
    }

    /**
     * Returns {@code true} as the chatbot should continue running after executing the "deadline" command.
     * @return {@code true}
     */
    @Override
    public boolean continueProcessing() {
        return true;
    }
}
