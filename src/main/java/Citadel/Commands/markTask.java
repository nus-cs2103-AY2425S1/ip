package Citadel.Commands;

import Citadel.Task.TaskList;
import Citadel.exception.CitadelException;
import Citadel.exception.CitadelInvalidArgException;
import Citadel.ui.TextUI;


public class markTask extends Command {
    public markTask(String input, TaskList tasks) {
        super(input, tasks);
    }

    @Override
    public void run() throws CitadelException {
        try {
            String[] words = input.split(" ");
            int index = Integer.parseInt(words[1]);
            tasks.get(index - 1).markAsDone();
            TextUI.printMark(tasks, index);
        } catch (IndexOutOfBoundsException e) {
            throw new CitadelInvalidArgException();
        }
    }
}
