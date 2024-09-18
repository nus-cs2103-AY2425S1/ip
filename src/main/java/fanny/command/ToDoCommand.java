package fanny.command;

import fanny.task.TaskList;
import fanny.task.ToDo;
import fanny.ui.Ui;

/**
 * Represents the command that handles the "todo" prompt.
 */
public class ToDoCommand extends Command {

    /** String representation of the description of the todo task. */
    private String description;
    /** The todo task to be created */
    private ToDo todo;

    /**
     * Constructs an {@code ToDoCommand} with the specified description.
     *
     * @param description The description of the todo task.
     */
    public ToDoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the command to add an todo task to the task list.
     * Handles any errors that may occur during parsing.
     *
     * @param list The task list to which the event task is added.
     * @param ui The UI object to interact with the user.
     * @return The message to be displayed after executing the command.
     */
    @Override
    public String executeCmd(TaskList list, Ui ui) {
        String message = "";
        ui.showHorizontalLine();

        try {
            generateToDo();
            list.add(this.todo);
            message = ui.showAddTaskMsg(this.todo, list);
        } catch (IllegalArgumentException e) {
            message = ui.showMessage(e.getMessage());
        } finally {
            ui.showHorizontalLine();
        }

        return message;
    }

    /**
     * Parses the description to extract the todo task information.
     * Generate a todo task based on the extracted information.
     *
     * @throws IllegalArgumentException if description is empty.
     */
    public void generateToDo() throws IllegalArgumentException {
        if (description.isEmpty()) {
            throw new IllegalArgumentException("Task description cannot be empty");
        }
        this.todo = new ToDo(this.description);
    }

    /**
     * Indicates that executing this command should not exit the application.
     *
     * @return {@code false}, indicating that the application should not exit.
     */
    @Override
    public boolean shouldExit() {
        return false;
    }
}
