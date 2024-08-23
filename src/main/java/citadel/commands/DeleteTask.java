package citadel.commands;

import citadel.Task.Task;
import citadel.Task.TaskList;
import citadel.exception.CitadelInvalidArgException;
import citadel.exception.CitadelException;
import citadel.ui.TextUI;

public class DeleteTask extends Command {
    public DeleteTask(String input, TaskList tasks) {
        super(input, tasks);
    }

    @Override
    public void run() throws CitadelException {
        try {
            String[] words = input.split(" ");
            int index = Integer.parseInt(words[1]);
            Task t = tasks.remove(index - 1);
            TextUI.printDelete(tasks, t);
        } catch (IndexOutOfBoundsException e) {
            throw new CitadelInvalidArgException();
        }
    };
}
