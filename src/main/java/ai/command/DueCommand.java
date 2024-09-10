package ai.command;

import java.time.LocalDate;

import ai.TaskList;
import ai.Ui;

/**
 * Executes the command that prints out the tasks that are due on the user specified date.
 */
public class DueCommand extends Command {
    private LocalDate date;

    public DueCommand(String arguments) {
        this.date = LocalDate.parse(arguments);
    }

    @Override
    public String execute(TaskList tasks, Ui ui) {
        return tasks.getDueTasks(date);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
