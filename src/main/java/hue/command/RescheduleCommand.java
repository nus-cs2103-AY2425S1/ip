package hue.command;

import hue.HueException;
import hue.ui.ui;
import hue.storage.Storage;
import hue.task.Task;
import hue.task.TaskList;

import java.io.IOException;

public class RescheduleCommand extends Command {
    private final int taskIndex;
    private final String newDate;

    public RescheduleCommand(String fullCommand) throws HueException {
        try {
            String[] parts = fullCommand.split(" ");
            this.taskIndex = Integer.parseInt(parts[1]) - 1;
            this.newDate = fullCommand.substring(fullCommand.indexOf(parts[2]));

        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new HueException("Please provide a valid task number and new date(s)");
        }
    }

    @Override
    public String execute(TaskList tasks, ui ui, Storage storage) throws HueException, IOException {
         assert taskIndex >= 0 && taskIndex < tasks.size() : "The task index is out of range";
         Task task = tasks.get(taskIndex);
         task.reschedule(newDate);
         storage.saveTasks(tasks);
         return ui.showRescheduleSuccess(task, newDate);
    }



}
