package Commands;

import Task.Task;
import Task.TaskList;
import exception.CitadelInvalidArgException;
import exception.CitadelException;

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
            System.out.println("Noted. I've removed this task:");
            System.out.println(t);
            System.out.println("Now you have " + tasks.size() + " tasks in the list");
        } catch (IndexOutOfBoundsException e) {
            throw new CitadelInvalidArgException();
        }
    };
}
