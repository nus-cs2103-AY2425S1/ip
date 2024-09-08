package main.command;

import main.exceptions.PrinceException;
import main.tasks.Deadline;
import main.tasks.TaskList;
import main.util.Storage;
import main.util.Ui;

/**
 * Creates a deadline task.
 */
public class DeadlineCommand extends Command {
    private String input;

    /**
     * Constructor for DeadlineCommand class.
     * @param input
     */
    public DeadlineCommand(String input) {
        this.input = input;
    }

    /**
     * Returns the string "deadline".
     * @param input Input of the user.
     * @return "deadline".
     */
    private String getDeadline(String input) {
        String[] arr = input.split("deadline|/by");
        String deadline = arr[1].trim();
        return deadline;
    }

    /**
     * Returns a String representing the date of the deadline.
     * @param input Input of the user.
     * @return Date of deadline.
     */
    private String getBy(String input) {
        String[] arr = input.split("/by");
        String by = arr[1].trim();
        return by;
    }

    /**
     * Creates a Deadline task.
     * Adds it to the list of tasks.
     * Saves task to storage.
     * Display output for user.
     * @param input    Input by the user.
     * @param taskList List of tasks.
     * @param storage  Storage.
     * @param ui Ui as initalised in main.
     * @throws PrinceException
     */
    private void handleDeadline(String input, TaskList taskList, Storage storage, Ui ui)
            throws PrinceException {
        if (input.equals("deadline")) {
            throw new PrinceException("Please describe your deadline task in more detail!");
        }
        ui.showAdd();
        String desc = getDeadline(input);
        String by = getBy(input);
        Deadline deadlineTask = new Deadline(desc, by);
        taskList.add(deadlineTask);
        ui.showTaskToString(deadlineTask);
        ui.showNumberOfTasks(taskList);
        storage.saveToFile(deadlineTask, taskList);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws PrinceException {
        handleDeadline(input, tasks, storage, ui);
    }
}
