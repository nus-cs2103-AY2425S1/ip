package commands;

import applemazer.Storage;
import applemazer.TaskList;
import applemazer.Ui;
import tasks.Task;

/**
 * Class that represents either the "mark", "unmark", or "delete" command.
 */
public class IntegerCommand extends Command {
    private final IntegerCommands command;
    private final int taskNumber;

    /**
     * Constructor for an {@code IntegerCommand} object.
     * <p>
     * Integer commands include "mark", "unmark", and "delete".
     * @param command The type of integer command (mark, unmark, delete).
     * @param taskNumber The task number to use.
     */
    public IntegerCommand(IntegerCommands command, int taskNumber) {
        this.command = command;
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the specified integer command.
     *
     * @param tasks   The task list to use if necessary.
     * @param storage The storage object containing the filepath which the chatbot saves to and loads from.
     * @param ui The Ui object used to generate the string to print.
     * @return The string to print.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        try {
            Task task = tasks.get(taskNumber);
            switch (command) {
            case Mark:
                task.setDone();
                storage.save();
                return ui.getTaskSetDoneMessage(task);
            case Unmark:
                task.setUndone();
                storage.save();
                return ui.getTaskSetUndoneMessage(task);
            case Delete:
                tasks.remove(task);
                storage.save();
                return ui.getTaskDeletedMessage(task, tasks.size());
            default:
                throw new IllegalStateException(); // This should never happen.
            }
        } catch (IndexOutOfBoundsException e) {
            if (tasks.isEmpty()) {
                return String.format("You have no tasks to %s.\n", command.toString().toLowerCase());
            } else {
                int size = tasks.size();
                return String.format("""
                                     You currently have %d tasks.
                                     Please enter a number between 1 and %d.
                                     """, size, size);
            }
        } catch (IllegalStateException e) {
            return e.getMessage();
        }
    }

    /**
     * Returns {@code true} as the chatbot should continue running after executing an integer command.
     * @return {@code true}
     */
    @Override
    public boolean continueProcessing() {
        return true;
    }
}
