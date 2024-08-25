package command;

import java.util.List;

import tasklist.TaskList;

import tasks.Task;

import ui.CommandLineUI;

public class ListCommand extends Command {
    protected Task task;

    public void execute(TaskList tasklist, CommandLineUI ui) {
        List<Task> tasks = tasklist.getTasks();

        for (int i = 0; i < tasks.size(); i++) {
            ui.speakLine((i + 1) + ". " + tasks.get(i));
        }

    }

    public boolean isExit() {
        return false;
    }
}
