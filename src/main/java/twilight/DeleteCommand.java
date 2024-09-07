package twilight;

import java.io.IOException;

public class DeleteCommand extends Command {
    protected int taskNum;

    public DeleteCommand(String details) {
        this.taskNum = Integer.valueOf(details) - 1;
    }

    public String execute(TaskList tasks, Storage storage) throws InvalidInputException {
        String output = tasks.delete(taskNum);
        try {
            storage.saveData(tasks.getTasks());
            return output;
        } catch (IOException e) {
            return "error saving";
        }
    }
}
