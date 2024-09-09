package bob.commands;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import bob.data.TaskList;
import bob.storage.Storage;
import bob.tasks.Task;
import bob.ui.Ui;

/**
 * Class representing the on command.
 */
public class On extends Command {
    private final LocalDate date;
    public On(LocalDate date) {
        this.date = date;
    }

    private String listTasksOnDate(TaskList list) {
        String dateFormatted = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        StringBuilder taskOnDate = new StringBuilder("Here are the tasks on " + dateFormatted + ":");

        int count = 0;
        for (Task t : list) {
            LocalDate taskDate = t.getDate();
            if (t.getDate() != (null) && taskDate.equals(date)) {
                taskOnDate.append("\n").append(++count).append(". ").append(t);
            }
        }

        if (count == 0) {
            return "There are no tasks on " + dateFormatted + ".";
        }

        return taskOnDate.toString();
    }

    @Override
    public String execute(TaskList list, Ui ui, Storage storage) {
        return listTasksOnDate(list);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
