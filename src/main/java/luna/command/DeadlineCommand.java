package luna.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import luna.LunaException;
import luna.Storage;
import luna.TaskList;
import luna.task.Deadline;
import luna.task.Task;

/**
 * Represents a command to add task with deadline to list of tasks.
 */
public class DeadlineCommand implements Command {
    private final Deadline deadline;
    private final Command previousCommand;

    /**
     * Creates a command to add task with deadline to list.
     *
     * @param inputs Inputs from user to create deadline task.
     */
    public DeadlineCommand(String[] inputs, Command previousCommand) throws LunaException {
        if (inputs.length == 1 || inputs[1].trim().isEmpty()
                || inputs[1].trim().indexOf("/") == 0) {
            throw new LunaException("Enter description for deadline\n"
                    + "e.g. deadline return book /by 12/12/2024 12:00");
        }

        String[] deadline = inputs[1].split(" /");

        if (deadline.length == 1) {
            throw new LunaException("Enter deadline for task\n"
                    + "e.g. deadline [task] /by [deadline]");
        }

        if (!deadline[1].contains("by ") || deadline[1].trim().length() <= 2) {
            throw new LunaException("Enter deadline for task\n"
                    + "e.g. deadline [task] /by [dd/MM/yyyy HH:mm]");
        }

        LocalDateTime deadlineDateTime;

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            deadlineDateTime = LocalDateTime.parse(deadline[1].substring(3), formatter);

            if (deadlineDateTime.isBefore(LocalDateTime.now())) {
                throw new LunaException("Invalid task: Deadline is before current time");
            }
        } catch (DateTimeParseException e) {
            throw new LunaException("Enter deadline using format: [dd/MM/yyyy HH:mm]\n"
                    + "e.g. 14/02/2024 14:30");
        }

        this.deadline = new Deadline(deadline[0].trim(), deadlineDateTime);
        this.previousCommand = previousCommand;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        return tasks.addTask(deadline, storage);
    }

    @Override
    public String undo(TaskList tasks, Storage storage) throws LunaException {
        int taskNumber = tasks.getTasks().indexOf(deadline);
        Task deleted = tasks.deleteTask(taskNumber, storage);
        return ">>> undo 'deadline' command\n"
                + "I've removed this task:\n"
                + "  " + deleted + "\n"
                + "Now you have " + tasks.getTasks().size() + " tasks in the list.";
    }

    @Override
    public Command getPreviousCommand() {
        return previousCommand;
    }
}
