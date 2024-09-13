package lebron;

import java.time.LocalDate;

public class SnoozeCommand extends Command {
    private int index;

    private LocalDate newDate;

    public SnoozeCommand(int index, LocalDate newDate) {
        this.index = index;
        this.newDate = newDate;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws LeBronException {
        taskList.rescheduleTask(index, newDate);
        Task task = taskList.getTask(index);
        String message = ui.showTaskRescheduled(task);
        storage.saveTasks(taskList);
        return message;
    }
    
}
