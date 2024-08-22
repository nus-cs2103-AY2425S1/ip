package Commands;

import Task.Task;
import Task.TaskList;
import exception.CitadelInvalidArgException;
import exception.CitadelException;
import ui.TextUI;

public class deleteTask extends Command {
    public deleteTask(String input, TaskList tasks) {
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
