package Commands;

import Task.TaskList;
import exception.CitadelException;
import exception.CitadelInvalidArgException;


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
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(tasks.get(index - 1));
        } catch (IndexOutOfBoundsException e) {
            throw new CitadelInvalidArgException();
        }
    };
}
