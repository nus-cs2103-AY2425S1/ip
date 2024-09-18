package command;

import exceptions.HimException;
import task.Task;
import task.TaskList;

public class ArchiveCommand extends Command {

    private int index;

    public ArchiveCommand(int index) {
        this.index = index;
    }


    @Override
    public String execute(TaskList list) {
        try {
            if (this.index == -1) {
                return him.Ui.say("Sure! Here's your archive:\n" + list.showArchive());
            }
            Task task = list.archive(index);
            return him.Ui.say("Archived " + task);
        } catch (HimException e) {
            return him.Ui.say(e.getMessage());
        }
    }
}
