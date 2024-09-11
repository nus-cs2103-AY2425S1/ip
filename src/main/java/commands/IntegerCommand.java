package commands;

import applemazer.Storage;
import applemazer.TaskList;
import applemazer.Ui;
import tasks.Deadline;
import tasks.DuplicateHandler;
import tasks.Task;
import tasks.Todo;
import tasks.Event;

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
        assert isValidIntegerCommand(command) : "The integer command is invalid.";
        this.command = command;
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the specified integer command.
     *
     * @param tasks            The task list to use if necessary.
     * @param storage          The storage object containing the filepath which the chatbot saves to and loads from.
     * @param ui               The Ui object used to generate the string to print.
     * @param duplicateHandler The duplicate handler to use if necessary.
     * @return The string to print.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui, DuplicateHandler duplicateHandler) {
        try {
            Task task = tasks.get(taskNumber);
            switch (command) {
            case Mark:
                task.setDone();
                storage.saveTaskList();
                return ui.getTaskSetDoneMessage(task);
            case Unmark:
                task.setUndone();
                storage.saveTaskList();
                return ui.getTaskSetUndoneMessage(task);
            case Delete:
                tasks.remove(task);
                if (task instanceof Todo) {
                    duplicateHandler.deleteTodo(task.toString());
                } else if (task instanceof Deadline) {
                    duplicateHandler.deleteDeadline(((Deadline) task).getKey());
                } else {
                    duplicateHandler.deleteEvent(((Event) task).getKey());
                }
                storage.saveTaskList();
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

    private boolean isValidIntegerCommand(IntegerCommands currentCommand) {
        for (IntegerCommands command : IntegerCommands.values()) {
            if (currentCommand == command) {
                return true;
            }
        }
        return false;
    }
}
