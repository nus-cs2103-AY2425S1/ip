package ai.command;

import ai.exception.AiException;
import ai.task.Task;
import ai.TaskList;
import ai.Ui;

public class DeleteCommand extends Command {
    private int i;

    public DeleteCommand(String index) {
        i = Integer.parseInt(index) - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws AiException {
        if (i < 0 || i >= tasks.size()) {
            throw new AiException("Hey hey!! The task doesn't exist... can't be removed >....<\n" +
                    "You might wanna try a valid number between 0 to " + tasks.size() + "\n");
        }

        Task temp = tasks.get(i);
        tasks.delete(i);
        System.out.println("Gotchyaa, task removed!!\n");
        System.out.println(temp + "\n");
        System.out.println("You have " + tasks.size() + " tasks in your list :p\n");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
