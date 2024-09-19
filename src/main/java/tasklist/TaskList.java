package tasklist;

import java.util.ArrayList;
import java.util.List;

import exceptions.DelphiException;
import exceptions.HardDriveNotFoundException;
import exceptions.InvalidInputException;
import exceptions.InvalidListItemException;
import parser.Parser;
import storage.Storage;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

/**
 * Represents a list of tasks. The tasklist.tasklist class manages the creation,
 * manipulation, and display of tasks, including Todos, Deadlines, and Events.
 *
 * @author Jordan Chan
 */
public class TaskList {
    private final List<Task> tasks;
    /**
     * Constructs an empty tasklist.tasklist.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the task list based on the input string.
     *
     * @param task The task object.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Converts all the strings that represent tasks from the hard drive .txt file and adds them to the task list.
     *
     * @param s The storage object that ui.Delphi uses to interact with the hard disk .txt file.
     */
    public void loadStorageToTasks(Storage s) throws HardDriveNotFoundException {
        Parser helperParser = new Parser();
        List<String> readTasks;
        readTasks = s.readFromHardDisk();
        int counter = 0;
        while (counter < readTasks.size()) {
            String line = readTasks.get(counter);

            //ensure that the stored tasks are formatted correcly as strings before converting
            //them to tasks
            assert line.charAt(0) == '[' : "Task incorrectly formatted";
            assert line.charAt(2) == ']' : "Task incorrectly formatted";
            assert line.charAt(3) == '[' : "Task incorrectly formatted";
            assert line.charAt(5) == ']' : "Task incorrectly formatted";
            assert (line.charAt(4) == 'X' || line.charAt(4) == ' ') : "Task incorrectly formatted";
            assert line.charAt(1) == 'T' || line.charAt(1) == 'D' || line.charAt(1) == 'E'
                    : "Task incorrectly formatted";

            try {
                if (helperParser.checkStringPrefix(line, 6, "[T][ ]")) {
                    tasks.add(new Todo(line.substring(7)));
                } else if (helperParser.checkStringPrefix(line, 6, "[T][X]")) {
                    tasks.add(new Todo(line.substring(7)));
                    markTaskAsDone(tasks.size()); //mark the newly loaded task as done
                } else if (helperParser.checkStringPrefix(line, 6, "[D][ ]")) {
                    String[] arr = helperParser.parseDeadline(helperParser.formatStringDeadline(line.substring(7)));
                    tasks.add(new Deadline(arr));
                } else if (helperParser.checkStringPrefix(line, 6, "[D][X]")) {
                    String[] arr = helperParser.parseDeadline(helperParser.formatStringDeadline(line.substring(7)));
                    tasks.add(new Deadline(arr));
                    markTaskAsDone(tasks.size()); //mark the newly loaded task as done
                } else if (helperParser.checkStringPrefix(line, 6, "[E][ ]")) {
                    String[] arr = helperParser.parseEvent(helperParser.formatStringEvent(line.substring(7)));
                    tasks.add(new Event(arr));
                } else if (helperParser.checkStringPrefix(line, 6, "[E][X]")) {
                    String[] arr = helperParser.parseEvent(helperParser.formatStringEvent(line.substring(7)));
                    tasks.add(new Event(arr));
                    markTaskAsDone(tasks.size()); //mark the newly loaded task as done
                }
            } catch (DelphiException e) {
                //empty because the assertions should make sure that the task is of the correct format
            }
            counter++;
        }
    }

    /**
     * Removes a task from the list based on the index.
     *
     * @param i The index of the task to be removed.
     * @return The removed task, or null if the index is invalid.
     */
    public Task removeTask(int i) throws DelphiException {
        if (i < 0) {
            throw new InvalidInputException(); // i == -1 indicates invalid input
        } else if (i <= tasks.size()) {
            Task t = tasks.get(i - 1);
            tasks.remove(i - 1);
            return t;
        } else {
            throw new InvalidListItemException(i);
        }
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Marks a task as done based on the index.
     *
     * @param i The index of the task to be marked as done.
     */
    public void markTaskAsDone(int i) throws DelphiException {
        if (i < 0) {
            throw new InvalidInputException(); // i == -1 indicates invalid input
        } else if (i <= tasks.size()) {
            tasks.get(i - 1).complete();
        } else {
            throw new InvalidListItemException(i);
        }
    }

    /**
     * Marks a task as undone based on the index.
     *
     * @param i The index of the task to be marked as undone.
     */
    public void markTaskAsUndone(int i) throws DelphiException {
        if (i < 0) {
            throw new InvalidInputException(); // i == -1 indicates invalid input
        } else if (i <= tasks.size()) {
            tasks.get(i - 1).uncomplete();
        } else {
            throw new InvalidListItemException(i);
        }
    }

    /**
     * Returns the string representation of a task based on the index.
     *
     * @param i The index of the task.
     * @return The string representation of the task, or an empty string if the index is invalid.
     */
    public Task getTask(int i) {
        if (i <= tasks.size()) {
            return tasks.get(i - 1);
        } else {
            return null;
        }
    }

    /**
     * returns task list.
     *
     * @return The task list as Collection.
     */
    public List<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Finds and returns a list of tasks that contain the specified keyword.
     *
     * @param keyword The keyword to search for in the tasks.
     * @return A list of tasks that contain the keyword.
     */
    public List<Task> findTask(String keyword) {
        List<Task> resTaskList = this.getTasks();
        TaskList res = new TaskList();
        for (int i = 0; i < resTaskList.size(); i++) {
            if (resTaskList.get(i).toString().contains(keyword)) {
                res.addTask(resTaskList.get(i));
            }
        }
        return res.getTasks();
    }

    /**
     * Prints all tasks in the task list.
     */
    public String printTasks() {
        String res = "";
        for (int i = 0; i < tasks.size(); i++) {
            res += "    " + (i + 1) + "." + tasks.get(i).toString() + "\n";
        }
        return res;
    }
}
