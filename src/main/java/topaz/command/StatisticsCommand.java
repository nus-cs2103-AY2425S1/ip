package topaz.command;

import topaz.main.Storage;
import topaz.main.TaskList;
import topaz.ui.Ui;

/**
 * Represents a command to statistic this week's work.
 * The command will return number of tasks finish this week and give feedback.
 */
public class StatisticsCommand extends Command {
    public StatisticsCommand() {
        super("statistic");
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        int count = tasks.countWeeklyComplete();
        if (tasks.getSize() == 0) {
            return ui.callAddTask();
        }
        if (count == 0) {
            return ui.showBadFeedback();
        } else {
            return ui.showGoodFeedback(count);
        }
    }
}
