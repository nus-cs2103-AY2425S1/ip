package ai.command;

import ai.TaskList;
import ai.Ui;

import java.time.LocalDate;

public class DueCommand extends Command {
    LocalDate date;

    public DueCommand(String arguments) {
        this.date = LocalDate.parse(arguments);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        tasks.isDue(date);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
