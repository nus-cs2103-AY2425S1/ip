package twilight;

import java.io.IOException;

public class MarkingCommand extends Command {
    protected int type;
    protected int taskNum;

    /**
     * Instantiates a marking command.
     *
     * @param type The type of marking command (mark or unmark) indicated by 1 or 2 respectively.
     * @param details The number of the task to be affected.
     */
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
