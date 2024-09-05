package rizzler.command;

import rizzler.Storage;
import rizzler.task.Task;
import rizzler.task.TaskLog;

public class ListCommand extends Command {

    public ListCommand() {
        super();
    }

    @Override
    public String[] execute(Storage storage, TaskLog taskLog) {
        Task[] tasks = taskLog.getLog();
        StringBuilder output = new StringBuilder();
        if (tasks.length == 0) {
            output.append("our list is empty right now dear, no tasks to list!\n");
        } else {
            output.append("these are the things we gotta do:\n");
            for (int i = 0; i < tasks.length; i++) {
                output.append(i + 1);
                output.append(". ");
                output.append(tasks[i]);
                output.append("\n");
            }
        }
        return output.toString().split("\n");
    }
}
