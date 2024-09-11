package commands;

import applemazer.Storage;
import applemazer.TaskList;
import applemazer.Ui;
import tasks.DuplicateHandler;
import tasks.Task;
import tasks.Todo;

/**
 * Class that represents the "to-do" command.
 */
public class TodoCommand extends Command {
    private final String desc;

    /**
     * Constructor for a {@code TodoCommand} object.
     * @param desc Description of the task.
     */
    public TodoCommand(String desc) {
        this.desc = desc;
    }

    /**
     * Executes the "to-do" command which adds a To-do task to the task list.
     *
     * @param tasks            The task list to use.
     * @param storage          The storage object containing the filepath which the chatbot saves to and loads from.
     * @param ui               The Ui object used to generate the string to print.
     * @param duplicateHandler The duplicate handler to use if necessary.
     * @return The string to print.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui, DuplicateHandler duplicateHandler) {
        Task task = new Todo(desc);
        tasks.add(task);
        duplicateHandler.addNewTodo(desc);
        storage.saveTaskList();
        return ui.getTaskAddedMessage(task, tasks.size());
    }

    /**
     * Returns {@code true} as the chatbot should continue running after executing the "to-do" command.
     * @return {@code true}
     */
    @Override
    public boolean continueProcessing() {
        return true;
    }
}
