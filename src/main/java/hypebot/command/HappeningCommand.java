package hypebot.command;

import hypebot.storage.StorageManager;
import hypebot.tasklist.Tasklist;
import hypebot.ui.Ui;

import java.time.LocalDate;

public class HappeningCommand extends Command{
    private LocalDate searchDate;

    public HappeningCommand(LocalDate date) {
        super();
        searchDate = date;
    }

    /**
     * @param tasks
     * @param ui
     * @param storageManager
     */
    @Override
    public void execute(Tasklist tasks, Ui ui, StorageManager storageManager) {
        Tasklist tasksHappening = tasks.getHappeningOn(searchDate);
        ui.showTasksHappeningOnDate(searchDate, tasksHappening);
    }
}
