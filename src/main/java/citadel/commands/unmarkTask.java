package citadel.commands;

import citadel.Task.TaskList;
import citadel.exception.CitadelInvalidArgException;
import citadel.exception.CitadelException;
import citadel.ui.TextUI;

public class unmarkTask extends Command {
    public unmarkTask(String input, TaskList tasks) {
        super(input, tasks);
    }

    @Override
    public void run() throws CitadelException {
        try {
            String[] words = input.split(" ");
            int index = Integer.parseInt(words[1]);
            tasks.get(index - 1).unMark();
            TextUI.printUnmark(tasks, index);
        } catch (IndexOutOfBoundsException e) {
            throw new CitadelInvalidArgException();
        }
    }
}

