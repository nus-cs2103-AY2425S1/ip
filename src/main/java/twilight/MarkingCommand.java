package twilight;

import java.io.IOException;

public class MarkingCommand extends Command {
    protected int type;
    protected int taskNum;

    public MarkingCommand(int type, String details) {
        this.type = type;
        this.taskNum = Integer.valueOf(details) - 1;
    }

    public void execute(TaskList tasks, Storage storage) throws InvalidInputException {
        if (type == 1) {
            System.out.println("Excellent I have marked it: " + tasks.mark(taskNum));
        } else {
            System.out.println("Fine I have unmarked it: " + tasks.unmark(taskNum));
        }
        try {
            storage.saveData(tasks.getTasks());
        } catch (IOException e) {
            System.out.println("error saving");
        }
    }
}
