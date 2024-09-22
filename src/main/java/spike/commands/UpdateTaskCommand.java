package spike.commands;

import spike.Spike;
import spike.exceptions.SpikeException;
import spike.parser.Parser;
import spike.storage.Storage;
import spike.storage.TaskList;
import spike.tasks.Task;
import spike.ui.Ui;

/**
 * Represents a command to update a task in the task list.
 */
public class UpdateTaskCommand extends Command {
    private final int taskIndex;
    private String taskType;
    private String updatedPart;
    private String updateType;

    /**
     * Creates a new UpdateTaskCommand with the given task index.
     *
     * @param taskIndex The index of the task to be deleted.
     */
    public UpdateTaskCommand(int taskIndex) {
        assert taskIndex >= 0 : "Task index cannot be negative";
        this.taskIndex = taskIndex;
        this.taskType = "";
    }

    /**
     * Returns the index of the task to be updated.
     *
     * @return The index of the task to be updated.
     */
    public int getTaskIndex() {
        return taskIndex;
    }

    /**
     * Returns the type of the task to be updated.
     *
     * @return The type of the task to be updated.
     */
    public String getTaskType() {
        return taskType;
    }

    /**
     * Sets the part of the task to be updated.
     *
     * @param updatedPart The part of the task to be updated.
     */
    public void setUpdatedPart(String updatedPart) {
        this.updatedPart = updatedPart;
    }

    /**
     * Sets the type of the task to be updated.
     *
     * @param updateType The type of the task to be updated.
     */
    public void setUpdateType(String updateType) {
        this.updateType = updateType;
    }

    /**
     * Updates the task from the task list, shows a message to the user, and writes the task list to the file.
     *
     * @param tasks The task list from which the task is to be updated.
     * @param ui The user interface to interact with the user.
     * @param storage The storage to write the task list to the file.
     * @throws SpikeException If an error occurs while updating the task from the task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SpikeException {
        try {
            this.taskType = tasks.getTaskType(taskIndex);
            assert ui != null : "User interface cannot be null";
            ui.showUpdateTaskRequest(taskType);
            assert storage != null : "Storage cannot be null";
            storage.writeToFile(tasks);
        } catch (IndexOutOfBoundsException e) {
            throw new SpikeException("Please enter a valid task number");
        }
    }

    /**
     * Updates the task from the task list, shows a message to the user, and writes the task list to the file.
     *
     * @param tasks The task list from which the task is to be updated.
     * @param ui The user interface to interact with the user.
     * @param storage The storage to write the task list to the file.
     * @param updateType The type of the task to be updated. (description, date time, end date time)
     * @param updatedPart The new value of the task to be updated.
     * @throws SpikeException If an error occurs while updating the task from the task list.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage, String updateType, String updatedPart) throws SpikeException {
        try {
            Task task = tasks.updateTask(taskIndex, updateType, updatedPart);
            assert ui != null : "User interface cannot be null";
            ui.showUpdatedTask(task);
            assert storage != null : "Storage cannot be null";
            storage.writeToFile(tasks);
        } catch (IndexOutOfBoundsException e) {
            throw new SpikeException("Please enter a valid task number");
        } catch (IllegalArgumentException e) {
            throw new SpikeException("Please enter a valid option");
        }
    }

    /**
     * Handles the user input when the command is in the state of awaiting reply.
     *
     * @param input The user input.
     * @param tasks The task list from which the task is to be updated.
     * @param ui The user interface to interact with the user.
     * @param storage The storage to write the task list to the file.
     * @throws SpikeException If an error occurs while updating the task from the task list.
     */
    public void handleAwaitingReply(String input, TaskList tasks, Ui ui, Storage storage) throws SpikeException {
        if (this.getTaskType().equals("todo")) {
            this.updateType = "description";
            this.updatedPart = Parser.validateUpdatedValue(updateType, input);
            this.execute(tasks, ui, storage, updateType, updatedPart);
            Spike.setSpikeState(Spike.SpikeState.COMPLETE); // Reset state
        } else {
            this.updateType = Parser.validateUpdateType(this.getTaskType(), input);
            ui.showUpdateTaskNewValueRequest(input);
            Spike.setSpikeState(Spike.SpikeState.UPDATE_AWAITING_NEW_VALUE);
        }
    }

    /**
     * Handles the user input when the command is in the state of awaiting new value.
     *
     * @param input The user input.
     * @param tasks The task list from which the task is to be updated.
     * @param ui The user interface to interact with the user.
     * @param storage The storage to write the task list to the file.
     * @throws SpikeException If an error occurs while updating the task from the task list.
     */
    public void handleAwaitingNewValue(String input, TaskList tasks, Ui ui, Storage storage) throws SpikeException {
        this.updatedPart = Parser.validateUpdatedValue(this.updateType, input);
        this.execute(tasks, ui, storage, this.updateType, this.updatedPart);
        Spike.setSpikeState(Spike.SpikeState.COMPLETE); // Reset state
    }

    /**
     * Returns whether the command is an exit command.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

