package llama.data;

import java.util.ArrayList;

import llama.exceptions.InvalidTaskException;
import llama.ui.Ui;

/**
 * Represents the list of tasks
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Creates a TaskList object
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Loads a task into the list. Does not show user any information about loading
     *
     * @param task a Task to be loaded in
     */
    public void loadTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Adds a task into the list. Shows user success message upon completion
     *
     * @param task a new Task to be added in
     * @param ui user interface to give user information
     */
    public String addTask(Task task, Ui ui) {
        String response = "";
        this.taskList.add(task);
        response += ui.displayString("Added: " + task);
        response += ui.displayString("You now have " + taskList.size() + " tasks.");
        return response;
    }

    /**
     * Deletes a task from the list. Shows user success message upon completion
     *
     * @param taskIndex task number of task to be deleted from
     * @param ui user interface to give user information
     * @throws InvalidTaskException if i < 0  or i > taskList.getSize()
     */
    public String deleteTask(int taskIndex, Ui ui) throws InvalidTaskException {
        String response = "";
        int numberOfTasks = this.taskList.size();
        if (taskIndex <= 0 || taskIndex > numberOfTasks) {
            throw new InvalidTaskException("Task does not exist!");
        }
        Task task = this.taskList.get(taskIndex - 1);
        this.taskList.remove(taskIndex - 1);
        numberOfTasks--;

        response += ui.displayString("Deleted: " + task);
        response += ui.displayString("You now have " + numberOfTasks + " tasks.");
        return response;
    }

    /**
     * Marks task as complete. Shows user success message upon completion.
     *
     * @param taskIndex task number of the task to be marked
     * @param ui user interface to give user information
     * @throws InvalidTaskException if i < 0  or i > taskList.getSize()
     */
    public String markTask(int taskIndex, Ui ui) throws InvalidTaskException {
        String response = "";
        int numberOfTasks = this.taskList.size();
        if (taskIndex <= 0 || taskIndex > numberOfTasks) {
            throw new InvalidTaskException("Task does not exist!");
        }
        Task task = this.taskList.get(taskIndex - 1);
        task.markDone();

        response += ui.displayString("Good Job! The task is now marked as done: ");
        response += ui.displayString("Marked task: " + task);
        return response;
    }

    /**
     * Marks task as not complete. Shows user success message upon completion.
     *
     * @param taskIndex task number of task to be unmarked
     * @param ui user interface to give user information
     * @throws InvalidTaskException if taskIndex < 0  or taskIndex > taskList.getSize()
     */
    public String unmarkTask(int taskIndex, Ui ui) throws InvalidTaskException {
        String response = "";
        int numberOfTasks = this.taskList.size();
        if (taskIndex <= 0 || taskIndex > numberOfTasks) {
            throw new InvalidTaskException("Task does not exist!");
        }
        Task task = this.taskList.get(taskIndex - 1);
        task.markNotDone();

        response += ui.displayString("Alright, the task is marked as not done: ");
        response += ui.displayString("Unmarked task: " + task);
        return response;
    }

    /**
     * Lists all tasks and shows them to user.
     *
     * @param ui user interface to show user information
     */
    public String listAllTasks(Ui ui) {
        String response = "";
        int numberOfTasks = taskList.size();
        response += ui.displayString("You currently have " + numberOfTasks + " tasks.");
        for (int i = 0; i < numberOfTasks; i++) {
            int num = i + 1;
            Task task = taskList.get(i);
            response += ui.displayString(num + ". " + task);
        }
        return response;
    }

    /**
     * Method to search and display tasks based on keyword given
     *
     * @param searchStr keyword to search tasks with
     * @param ui user interface to give user information
     */
    public String searchTasks(String searchStr, Ui ui) {
        String response = "";
        int numberOfTasks = taskList.size();
        int num = 1;
        response += ui.displayString("Here are the tasks with keyword '" + searchStr + "':");
        for (int i = 0; i < numberOfTasks; i++) {
            Task task = taskList.get(i);
            if (task.contains(searchStr)) {
                response += ui.displayString(num + ". " + task);
                num++;
            }
        }
        return response;
    }

    /**
     * Method to get number of tasks
     *
     * @return number of tasks in list
     */
    public int getSize() {
        return taskList.size();
    }

    /**
     * Method to get a specific tasks
     *
     * @param taskIndex task to get based on index
     * @return Task at given index
     */
    public Task getTask(int taskIndex) {
        return taskList.get(taskIndex);
    }

}
