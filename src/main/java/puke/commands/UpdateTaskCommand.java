package puke.commands;

import puke.Storage;
import puke.TaskList;
import puke.exceptions.EmptyDescriptionException;
import puke.exceptions.TaskNumberOutOfBoundsException;
import puke.exceptions.UpdateTaskFailedException;
import puke.message.MessageBuilder;
import puke.task.Deadline;
import puke.task.Event;
import puke.task.Task;

/**
 * Command for updating attributes of existing tasks in the task list.
 * This command allows the updating of specific attributes such as name, deadlines, and event times,
 * based on the task type.
 */
public class UpdateTaskCommand extends Command {
    private int taskIndex;
    private String updateType;
    private String newValue;

    /**
     * Constructs a new UpdateTaskCommand from a command string.
     *
     * @param args The argument string containing the task index, update type, and new value.
     * @throws EmptyDescriptionException If the command string is empty.
     * @throws UpdateTaskFailedException If the command string does not contain sufficient
     *                                   parts to form a valid update command.
     */
    public UpdateTaskCommand(String args) throws EmptyDescriptionException, UpdateTaskFailedException {
        if (args.isEmpty()) {
            throw new EmptyDescriptionException();
        }
        String[] parts = args.split(" ", 3);
        if (parts.length < 3) {
            throw new UpdateTaskFailedException("missing update info");
        }
        this.taskIndex = Integer.parseInt(parts[0]) - 1;
        this.updateType = parts[1].substring(1);
        this.newValue = parts[2];
    }

    /**
     * Executes the update command to modify attributes of a specific task in the task list.
     *
     * @param taskList The TaskList containing all tasks.
     * @param messageBuilder The message builder for constructing return messages.
     * @return A message indicating the result of the operation.
     * @throws UpdateTaskFailedException If the update operation is invalid or fails.
     * @throws TaskNumberOutOfBoundsException If the specified task index is out of bounds.
     */
    @Override
    public String execute(TaskList taskList, MessageBuilder messageBuilder) throws UpdateTaskFailedException,
            TaskNumberOutOfBoundsException {
        try {
            Task task = taskList.getTask(taskIndex);
            switch (updateType) {
            case "name":
                task.setDescription(newValue);
                break;
            case "by":
                if (!(task instanceof Deadline)) {
                    throw new UpdateTaskFailedException("Cannot update 'by' for non-deadline tasks. Task type is: "
                            + task.getClass().getSimpleName());
                } ((Deadline) task).setBy(newValue);
                break;
            case "from":
                if (!(task instanceof Event)) {
                    throw new UpdateTaskFailedException("Cannot update 'from' for non-event tasks. Task type is: "
                            + task.getClass().getSimpleName());
                } ((Event) task).setFrom(newValue);
                break;
            case "to":
                if (!(task instanceof Event)) {
                    throw new UpdateTaskFailedException("Cannot update 'to' for non-event tasks. Task type is: "
                            + task.getClass().getSimpleName());
                } ((Event) task).setTo(newValue);
                break;
            default:
                throw new UpdateTaskFailedException("Invalid update type.");
            }
            Storage.saveTasks(taskList.getTasks());
            return "Task updated successfully: " + task;
        } catch (NumberFormatException e) {
            throw new TaskNumberOutOfBoundsException(taskIndex);
        } catch (Exception e) {
            throw new UpdateTaskFailedException(e.getMessage());
        }
    }
}
