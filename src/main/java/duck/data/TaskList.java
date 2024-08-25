package duck.data;

import duck.data.exception.DuckException;
import duck.data.task.Task;
import duck.storage.Storage;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TaskList extends ArrayList<Task> {
    private static final String FILE_PATH = "data/duck.txt";




    public void addTask(Task task, Storage storage) throws DuckException {
        this.add(task);
        storage.appendTask(task);
        System.out.println("Got it. I've added this task:\n" + task);
        System.out.println("Now you have " + this.size() + " tasks in the list.\n");
    }

    public void deleteTask(int index, Storage storage) throws DuckException {
        System.out.println("Noted. I've removed this task:\n"
                + this.get(index));
        this.remove(index);
        storage.writeTasks(this);
        System.out.println("Now you have " + this.size() + " tasks in the list.\n");
    }

}
