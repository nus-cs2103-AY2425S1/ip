package assistinator.commands;

import java.time.DateTimeException;
import java.time.LocalDateTime;

import assistinator.AssistinatorException;
import assistinator.Storage;
import assistinator.TaskList;
import assistinator.tasks.DeadlineTask;

/**
 * Represents deadline command.
 */
public class DeadlineCommand extends Command {
    static final int DEADLINE_INPUT_LENGTH = 2;

    public DeadlineCommand(String input) {
        super(input);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws AssistinatorException {
        DeadlineTask newDeadline = parseDeadline(input);
        tasks.addTask(newDeadline);
        return String.format(
                "I have added this task:\n%s\nYour evil agenda contains %d%s",
                newDeadline,
                tasks.size(),
                tasks.size() == 1 ? " task" : " tasks"
        );
    }

    private DeadlineTask parseDeadline(String input) throws AssistinatorException {
        String[] parts = input.split(" /");
        if (parts.length != DEADLINE_INPUT_LENGTH || parts[1].equals("by")) {
            throw new AssistinatorException("Please follow format: deadline {task description} /by {deadline}");
        }
        String description = parts[0].substring(parts[0].indexOf(' ') + 1).trim();
        if (description.isEmpty()) {
            throw new AssistinatorException("Task description cannot be empty");
        }
        LocalDateTime deadline = LocalDateTime.parse(parts[1].substring(parts[1].indexOf(' ') + 1), formatter);
        try {
            return new DeadlineTask(description, deadline);
        } catch (DateTimeException e) {
            throw new AssistinatorException("Invalid date or time");
        }
    }
}
