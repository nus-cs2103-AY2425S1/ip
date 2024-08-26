package Command;
import TaskList.TaskList;
import TaskList.IncorrectStateException;

import static Ui.Ui.printMessage;

public class MarkCommand extends Command{
    private TaskList list;
    private int index;

    public MarkCommand (TaskList list, int index) {
        this.list = list;
        this.index = index;
    }

    @Override
    public void execute() {
        if (list.size() < index || index <= 0 ) {
            printMessage("Sorry~ can not mark that task because it does not exist");
        } else {
            try {
                list.mark(index - 1);
                printMessage(String.format("Nice Job, Job %s has been marked as done!\n    %s",index, list.get(index - 1)));
            } catch (IncorrectStateException e) {
                printMessage(e.getMessage());
            }
        }
    }
}
