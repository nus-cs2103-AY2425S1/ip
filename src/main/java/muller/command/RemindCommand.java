package muller.command;

import java.time.LocalDate;
import java.util.List;

import muller.storage.Storage;
import muller.task.Task;
import muller.task.TaskList;
import muller.ui.Ui;

/**
 * Command to remind users about upcoming deadlines and events.
 */
public class RemindCommand extends Command {
    private int daysBeforeReminder;

    /**
     * Constructs a RemindCommand with the specified inputs.
     *
     * @param inputs The command inputs.
     * @throws MullerException If the input date format is invalid.
     */
    public RemindCommand(String[] inputs) throws MullerException {
        if (CommandUtil.isRemindCommandNotValid(inputs)) {
            throw new MullerException("Specify the number of days before reminder!");
        }
        try {
            daysBeforeReminder = Integer.parseInt(inputs[1].trim());
        } catch (Exception e) {
            throw new MullerException("Invalid format. Use 'remind [number of days before reminder]'.");
        }
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        LocalDate today = LocalDate.now();
        LocalDate upcoming = today.plusDays(daysBeforeReminder);

        List<Task> reminders = tasks.getTasksDueSoon(today, upcoming);
        if (reminders.isEmpty()) {
            return "No upcoming tasks in the next " + daysBeforeReminder + " days.";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks due in the next " + daysBeforeReminder + " days:\n");
        for (int i = 0; i < reminders.size(); i++) {
            sb.append((i + 1) + ": " + reminders.get(i) + "\n");
        }
        return sb.toString();
    }
}

