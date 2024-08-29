package fanny.command;

import fanny.task.TaskList;
import fanny.task.Task;
import fanny.task.ToDo;
import fanny.ui.Ui;

/**
 * Represents the command that handles the "todo" prompt.
 */
public class ToDoCommand extends Command {

    /** String representation of the description of the todo task. */
    private String description;

    /**
     * Constructs an {@code ToDoCommand} with the specified description.
     *
     * @param description The description of the todo task.
     *
     */
    public ToDoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the command to add an todo task to the task list.
     * Parses the description to extract the todo task information.
     * Handles any errors that may occur during parsing.
     *
     * @param list The task list to which the event task is added.
     * @param ui The UI object to interact with the user.
     */
    @Override
    public void actionable(TaskList list, Ui ui) {
        ui.showHorizontalLine();
        try {
            Task todo = new ToDo(description);
            list.add(todo);
            ui.showMessage("Fanny:\nGot it. I've added this task:");
            ui.showMessage(todo.toString());
            ui.showMessage("Now you have " + list.getLength() + " tasks in the list.");
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.showMessage("Task description cannot be empty");
        } finally {
            ui.showHorizontalLine();
        }
    }

    /**
     * Indicates that executing this command should not exit the application.
     *
     * @return {@code false}, indicating that the application should not exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
