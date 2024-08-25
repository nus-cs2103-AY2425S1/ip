package duck.commands;

import duck.data.TaskList;
import duck.data.exception.DuckException;
import duck.data.task.Deadline;
import duck.storage.Storage;
import duck.ui.Ui;
import duck.util.Utils;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeadlineCommand extends Command {

    public DeadlineCommand(String message) {
        super(message);
    }
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws DuckException {
        Deadline deadline = parseDeadline(message);
        tasks.addTask(deadline, storage);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    private Deadline parseDeadline(String input) throws DuckException {
        // Regular expression to match the pattern for deadline
        Pattern pattern = Pattern.compile("(?i)^deadline\\s+(.+)\\s+/by\\s+(.+)$");
        Matcher matcher = pattern.matcher(input);

        if (matcher.matches()) {
            String description = matcher.group(1);
            String deadlineStr = matcher.group(2);
            LocalDateTime deadline = Utils.convertToDateTime(deadlineStr);
            return new Deadline(description, deadline);
        }
        else {
            throw new DuckException("Hey, a deadline instruction should be of the following format:\n"
                    + "deadline {description} /by {deadline}\n"
                    + "{deadline} should be in the format yyyy-mm-dd HHmm OR yyyy/mm/dd HHmm\n");
        }
    }
}
