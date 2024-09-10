package nextgpt.command;


import nextgpt.Storage;
import nextgpt.TaskList;
import nextgpt.Ui;
import nextgpt.task.Event;
import nextgpt.task.Task;

import java.time.LocalDate;

/**
 * Subclass of Command that displays events containing a specific date
 */
public class ScheduleCommand extends Command {

    LocalDate specificDate;

    TaskList specificEvents;
    public ScheduleCommand (LocalDate specificDate) {
        this.specificDate = specificDate;
        specificEvents = new TaskList();
    }

    /**
     * Outputs a list of events that contains the specific date
     *
     * @return String message with list of events that contains the specific date to be displayed
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert ui != null: "Storage cannot be NULL!";
        assert tasks != null: "Ui cannot be NULL!";
        assert storage != null: "Task list cannot be NULL!";


        for (int i = 0; i < tasks.size(); i++) {
            Task currTask = tasks.get(i);
            if (currTask.isDone() || !(currTask instanceof Event)) {
                continue;
            }

            LocalDate startingDate = ((Event) currTask).getStart();
            LocalDate endingDate = ((Event) currTask).getEnd();
            if (specificDate.isAfter(startingDate) && specificDate.isBefore(endingDate)) {
                specificEvents.add(currTask);
            }

        }
        return ui.showList(specificEvents);
    }
    @Override
    public boolean isExit() {
        return false;
    }

}
