package bob.commands;

import bob.storage.Storage;
import bob.tasks.Task;
import bob.data.TaskList;
import bob.ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Lists tasks on a specific date.
 */
public class On extends Command {
    LocalDate date;
    public On(LocalDate date) {
        this.date = date;
    }

    private void listTasksOnDate(TaskList list) {
        int count = 0;
        String dateFormatted = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        StringBuilder taskOnDate = new StringBuilder("Here are the tasks on " + dateFormatted + ":");
        for (Task t : list) {
            LocalDate taskDate = t.getDate();
            if (t.getDate()!=(null) && taskDate.equals(date)) {
                taskOnDate.append("\n").append(++count).append(". ").append(t);
            }
        }
        if (count == 0) {
            System.out.println("There are no tasks on " + dateFormatted + ".");
            return;
        }
        System.out.println(taskOnDate);
    }
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        listTasksOnDate(list);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
