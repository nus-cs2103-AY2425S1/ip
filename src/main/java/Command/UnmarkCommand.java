package Command;
import TaskList.TaskList;
import TaskList.IncorrectStateException;

import static Ui.Ui.printMessage;

public class UnmarkCommand extends Command{
    private TaskList list;
    private int index;

    public UnmarkCommand (TaskList list, int index) {
        this.list = list;
        this.index = index;
    }

    @Override
    public void execute() {
        if (list.size() < index || index <= 0 ) {
            printMessage("Sorry~ can not unmark that task because it does not exist");
        } else {
            try {
                list.unmark(index - 1);
                printMessage(String.format("Alright, Job %s has been marked as not done!\n    %s",
                        index, list.get(index - 1)));
            } catch (IncorrectStateException e) {
                printMessage(e.getMessage());
            }
        }
    }
}