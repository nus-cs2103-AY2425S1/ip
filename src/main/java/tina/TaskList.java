package tina;

import java.util.ArrayList;

import tina.task.Task;

/**
 * The <code>TaskList</code> class manages a list of tasks and handles operations such as
 * adding, marking, unmarking, listing, and deleting tasks.
 * It interacts with the <code>Storage</code> class to persist the task list to a file.
 */
public class TaskList {
    private final ArrayList<Task> list;
    private final Storage storage;

    /**
     * Constructs a new <code>TaskList</code> object with a specified <code>Storage</code> object.
     * The task list is initialized by reading from storage file.
     *
     * @param storage The <code>Storage</code> object that handles file operations for the task list.
     */
    public TaskList(Storage storage) {
        this.storage = storage;
        this.list = storage.read();
    }

    /**
     * Adds a task to the list and saves the updated list to storage.
     * Displays a confirmation message including the new task and the total number of tasks.
     *
     * @param task The task to be added to the list.
     */
    public String addTask(Task task) throws TinaException {
        if (checkRepeat(task)) {
            throw new TinaException("Task has already been added!");
        } else {
            list.add(task);
            storage.write(list);
            String result = "Got it. I've added this task:\n"
                    + "  " + task.getDes() + "\n"
                    + String.format("Now you have %d tasks in the list.", list.size());
            return result;
        }
    }

    /**
     * Marks a task as completed and saves the updated list to storage.
     * Displays a confirmation message including the marked task.
     *
     * @param x The index of the task to be marked as completed.
     */
    public String markTask(int x) {
        Task currTask = list.get(x - 1);
        currTask.mark();
        String result = "Nice! I've marked this task as done:\n"
                + "  "
                + currTask.getDes();

        storage.write(list);
        return result;
    }

    /**
     * Unmarks a task and saves the updated list to storage.
     * Displays a confirmation message including the unmarked task.
     *
     * @param x The index of the task to be unmarked.
     */
    public String unmarkTask(int x) {
        Task currTask = list.get(x - 1);
        currTask.unmark();
        String result = "OK, I've marked this task as not done yet:\n"
                + "  " + currTask.getDes();
        storage.write(list);
        return result;
    }

    /**
     * Lists all tasks in the task list with their current status.
     * Displays the tasks with their respective indices.
     */
    public String listTask() {
        StringBuilder result = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < list.size(); i++) {
            Task currTask = list.get(i);
            result.append(i + 1).append(". ").append(currTask.getDes());
            if (i < list.size() - 1) {
                result.append("\n");
            }
        }
        return result.toString();
    }
    /**
     * Deletes a task from the list and saves the updated list to storage.
     * Displays a confirmation message including the deleted task and the total number of remaining tasks.
     *
     * @param x The index of the task to be deleted.
     */
    public String deleteTask(int x) {
        Task currTask = list.remove(x - 1);
        String result = "Noted. I've removed this task:\n"
                + "  " + currTask.getDes() + "\n"
                + String.format("Now you have %d tasks in the list.", list.size());
        storage.write(list);
        return result;
    }


    /**
     * Finds and returns a list of tasks that match the given input string.
     * The search is performed by checking if the input string is contained
     * within the description of each task.
     *
     * @param input The string to search for within the task descriptions.
     *              This should not be null.
     * @return A formatted string listing all tasks that match the input string.
     *         The string includes task numbers and descriptions. If no tasks
     *         match, the returned string will indicate that no matches were found.
     */
    public String findTask(String input) {
        StringBuilder result = new StringBuilder("Here are the matching tasks in your list:\n");
        int count = 1;
        for (int i = 0; i < list.size(); i++) {
            Task currTask = list.get(i);
            if (currTask.description().contains(input)) {
                result.append(count).append(". ").append(currTask.getDes()).append("\n");
                count++;
            }
        }
        return result.toString();
    }
    private boolean checkRepeat(Task task) {
        String taskString = Parser.removeThirdChar(task.toString()); //to ignore whether task is marked
        for (Task t : list) {
            if (Parser.removeThirdChar(t.toString()).equals(taskString)) {
                return true;
            }
        }
        return false;
    }
}
