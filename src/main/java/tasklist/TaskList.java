package tasklist;

import java.util.ArrayList;
import java.util.List;

import command.CommandNotFoundException;
import ouiouibaguette.OuiOuiBaguetteException;
import storage.Storage;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

public class TaskList {

    protected Storage storage;

    protected List<Task> tasklist;

    public TaskList(Storage storage) {
        this.storage = storage;

        tasklist = new ArrayList<Task>();

        // Load data from storage
        List<String> data = storage.load();

        for (String line : data) {

            try {
                // Escape |
                String[] taskParts = line.split(" \\| ");
                Task task = null;
                if (taskParts[0].equals("todo")) {
                    task = new ToDo(taskParts[2]);
                    if (Integer.parseInt(taskParts[1]) == 1) {
                        task.mark();
                    }
                } else if (taskParts[0].equals("deadline")) {
                    task = new Deadline(taskParts[2], taskParts[3]);
                    if (Integer.parseInt(taskParts[1]) == 1) {
                        task.mark();
                    }
                } else if (taskParts[0].equals("event")) {
                    task = new Event(taskParts[2], taskParts[3], taskParts[4]);
                    if (Integer.parseInt(taskParts[1]) == 1) {
                        task.mark();
                    }
                } else {
                    throw new CommandNotFoundException("Command not found: " + taskParts[0]);
                }
                // Add task to return result
                if (task != null) {
                    tasklist.add(task);
                }
            } catch (OuiOuiBaguetteException e) {
                // Do nth
            }

        }

    }


    public Task addTask(Task task) {
        // Update ArrayList
        tasklist.add(task);

        // Update storage
        storage.store(task.toDataFormat());

        return task;
    }


    public Task getTask(int index) {
        if (index < 0 || index >= tasklist.size()) {
            throw new IndexOutOfBoundsException();
        }

        Task task = tasklist.get(index);

        return task;
    }


    public Task mark(int index) {
        if (index < 0 || index >= tasklist.size()) {
            throw new IndexOutOfBoundsException();
        }

        Task task = tasklist.get(index);

        task.mark();

        // Update storage
        storage.update(toDataFormat());

        return task;
    }


    public Task unmark(int index) {
        if (index < 0 || index > tasklist.size()) {
            throw new IndexOutOfBoundsException();
        }

        Task task = tasklist.get(index);

        task.unmark();

        // Update storage
        storage.update(toDataFormat());

        return task;
    }


    public Task delete(int index) {
        if (index < 0 || index >= tasklist.size()) {
            throw new IndexOutOfBoundsException();
        }

        Task task = tasklist.remove(index);

        // Update storage
        storage.update(toDataFormat());

        return task;
    }


    // Utils
    public int size() {
        return tasklist.size();
    }

    public List<Task> getTasks() {
        return tasklist;
    }

    public List<String> toDataFormat() {
        List<String> res = new ArrayList<String>();

        for (Task t : tasklist) {
            res.add(t.toDataFormat());

        }

        return res;
    }

    // public static void main(String[] args) {
    //     TaskList taskList = new TaskList(new FileStorage("data"));

    //     taskList.unmark(1);

    //     System.out.println(taskList);
    // }
}
