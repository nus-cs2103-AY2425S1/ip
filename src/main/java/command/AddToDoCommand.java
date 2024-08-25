package command;

import tasklist.TaskList;

import tasks.Task;
import tasks.ToDo;

import ui.CommandLineUI;

public class AddToDoCommand extends Command {
    protected Task task;

    public AddToDoCommand(String desc) {
        task = new ToDo(desc);
        
    }

    public void execute(TaskList tasklist, CommandLineUI ui) {
        tasklist.addTask(task);

        ui.speakLine("Got it. I've added this task:");
        ui.speakLine("  " + task);
        ui.speakLine("Now you have " + tasklist.size() + " tasks in the list.");

    }

    public boolean isExit() {
        return false;
    }
}
