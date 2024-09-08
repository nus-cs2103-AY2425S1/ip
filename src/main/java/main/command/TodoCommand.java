package main.command;

import main.exceptions.PrinceException;
import main.tasks.TaskList;
import main.tasks.Todo;
import main.util.Storage;
import main.util.Ui;

/**
 * Creates a todo task.
 */
public class TodoCommand extends Command {
    private String input;

    /**
     * Constructor for the TodoCommand class.
     * @param input
     */
    public TodoCommand(String input) {
        this.input = input;
    }

    /**
     * Returns the string "todo".
     * @param input Input of the user.
     * @return "todo".
     */
    private String getTodo(String input) {
        String[] arr = input.split("todo");
        String todo = arr[1].trim();
        return todo;
    }

    /**
     * Creates a Todo task.
     * Adds it to the list of tasks.
     * Saves task to storage.
     * Displays output for user.
     * @param input    Input by the user.
     * @param taskList List of tasks.
     * @param storage  Storage.
     * @param ui Ui as initialised in main.
     * @throws PrinceException
     */
    private void handleTodo(String input, TaskList taskList, Storage storage, Ui ui)
            throws PrinceException {
        if (input.equals("todo")) {
            throw new PrinceException("Please describe your todo task in more detail!");
        }
        ui.showAdd();
        String desc = getTodo(input);
        Todo todo = new Todo(desc);
        taskList.add(todo);
        ui.showTaskToString(todo);
        ui.showNumberOfTasks(taskList);
        storage.saveToFile(todo, taskList);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws PrinceException {
        handleTodo(this.input, tasks, storage, ui);
    }
}
