package bob.commands;

import java.time.LocalDateTime;

import bob.data.TaskList;
import bob.storage.Storage;
import bob.tasks.Task;
import bob.ui.Ui;

public class Remind extends Command {
    private final LocalDateTime dueDate = LocalDateTime.now().plusDays(7);

    public String getReminders(TaskList taskList) {
        StringBuilder reminders = new StringBuilder("Here are your upcoming reminders:\n");
        int count = 0;

        for (Task t : taskList) {
            if ((t.getType().equals("D") || t.getType().equals("E")) && !t.getIsDone()) {
                if (t.getReminderDate().isBefore(dueDate)) {
                    reminders.append(++count).append(". ").append(t).append("\n");
                }
            }
        }

        if (count == 0) {
            return "No upcoming tasks!";
        }

        return reminders.toString().trim();
    }

    @Override
    public String execute(TaskList list, Ui ui, Storage storage) {
        return getReminders(list);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
