package Commands;

import Task.TaskList;
import exception.CitadelInvalidArgException;
import exception.CitadelException;

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
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(tasks.get(index - 1));
        } catch (IndexOutOfBoundsException e) {
            throw new CitadelInvalidArgException();
        }
    }
}

