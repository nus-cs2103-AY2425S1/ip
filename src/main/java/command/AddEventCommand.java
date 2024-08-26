package command;

import ouiouibaguette.OuiOuiBaguetteException;
import tasklist.TaskList;
import tasks.Event;
import tasks.Task;
import ui.CommandLineUI;

public class AddEventCommand extends Command {
    protected Task task;

    public AddEventCommand(String desc, String start, String end) throws OuiOuiBaguetteException {
        task = new Event(desc, start, end);

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
