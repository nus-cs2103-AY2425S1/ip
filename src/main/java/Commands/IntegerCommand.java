package commands;

import applemazer.Storage;
import applemazer.TaskList;
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
     * @param tasks   The task list to use if necessary.
     * @param storage The storage object containing the filepath which the chatbot saves to and loads from.
     */
    @Override
    public void execute(TaskList tasks, Storage storage) {
        try {
            Task task = tasks.get(taskNumber);
            switch (command) {
                case Mark :
                    task.setDone();
                    task.printTaskSetDoneMessage();
                    storage.save();
                    break;
                case Unmark :
                    task.setUndone();
                    task.printTaskSetUndoneMessage();
                    storage.save();
                    break;
                case Delete :
                    tasks.remove(task);
                    task.printTaskDeletedMessage();
                    storage.save();
                    break;
            }
        } catch (IndexOutOfBoundsException e) {
            if (tasks.isEmpty()) {
                String emptyMessage = String.format("You have no tasks to %s.\n", command.toString().toLowerCase());
                System.err.println(emptyMessage);
            } else {
                int size = tasks.size();
                String message = String.format("""
                                               You currently have %d tasks.
                                               Please enter a number between 1 and %d.
                                               """, size, size);
                System.err.println(message);
            }
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
