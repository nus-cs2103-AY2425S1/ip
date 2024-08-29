package twilight;

import java.io.IOException;

public class DeleteCommand extends Command {
    protected int taskNum;

    public DeleteCommand(String details) {
        this.taskNum = Integer.valueOf(details) - 1;
    }

    public void execute(TaskList tasks, Storage storage) throws InvalidInputException {
        System.out.println(tasks.delete(taskNum));
        try {
            storage.saveData(tasks.getTasks());
        } catch (IOException e) {
            System.out.println("error saving");
        }
    }
}
