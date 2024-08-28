package commands;

import applemazer.Storage;
import applemazer.TaskList;
import tasks.Task;

public class FindCommand extends Command {
    private final String desc;

    public FindCommand(String desc) {
        this.desc = desc;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) {
        int taskNumber = 1;
        System.out.println("Here are the matching tasks in your list:");
        for (Task task : tasks.getList()) {
            if (task.getDescription().contains(desc)) {
                System.out.println(taskNumber + "." + task.getStatusIcon() + task);
            }
            taskNumber++;
        }
        System.out.println(); // Leave an empty line.
    }

    @Override
    public boolean continueProcessing() {
        return true;
    }
}
