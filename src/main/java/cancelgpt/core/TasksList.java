package cancelgpt.core;

import java.util.ArrayList;
import java.util.List;

import cancelgpt.exception.task.TaskDoesNotExist;
import cancelgpt.task.Task;

/**
 * Represents the task list for CancelGPT chatbot.
 */
public class TasksList {
    private final List<Task> tasksList;

    /**
     * Initialises the TasksList, which initially has no tasks.
     */
    public TasksList() {
        this.tasksList = new ArrayList<>();
    }

    /**
     * Adds the task to the list and returns
     * the string representation of the task added.
     *
     * @param task the task to add to the list
     * @return the string representation of the task added
     */
    public String addToTaskList(Task task) {
        this.tasksList.add(task);
        return task.toString();
    }

    /**
     * Returns string response of all the tasks in tasks list.
     */
    public String displayTasksList() {
        StringBuilder tasksListResponse = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < this.tasksList.size(); i++) {
            tasksListResponse.append(i + 1).append(". ").append(this.tasksList.get(i)).append("\n");
        }
        return tasksListResponse.toString();
    }

    /**
     * Returns the internal list to store the tasks.
     *
     * @return the list to store the tasks
     */
    public List<Task> getTasksList() {
        return List.copyOf(this.tasksList);
    }

    /**
     * Returns the size of the task list
     *
     * @return the size of the list
     */
    public int getSize() {
        return this.tasksList.size();
    }

    /**
     * Deletes a task identified by its task number from the list,
     * and prints the task deleted.
     *
     * @param taskNumber the task number in the task list
     * @throws TaskDoesNotExist if the task to delete does not exist
     */
    public String deleteTask(int taskNumber) throws TaskDoesNotExist {
        if (taskNumber <= 0 || taskNumber > this.tasksList.size()) {
            throw new TaskDoesNotExist();
        }
        Task taskDeleted = this.tasksList.remove(taskNumber - 1);

        return "Noted. I've removed this task: " + taskDeleted.toString()
                + "Now you have " + this.tasksList.size() + " tasks in the list.";
//        UI.printMessageToConsole("Noted. I've removed this task:");
//        UI.printMessageToConsole(" " + taskDeleted);
//        UI.printMessageToConsole("Now you have " + this.tasksList.size() + " tasks in the list.");
    }

    /**
     * Marks the task identified by its task number in the task list.
     * Does nothing if the task is already marked.
     *
     * @param taskNumber the task number in the chatbot's task list
     * @throws TaskDoesNotExist if the task does not exist in the task list
     */
    public String markTask(int taskNumber) throws TaskDoesNotExist {
        if (taskNumber <= 0 || taskNumber > this.tasksList.size()) {
            throw new TaskDoesNotExist();
        }

        this.tasksList.get(taskNumber - 1).mark();

        return "Nice! I've marked this task as done: "
                + this.tasksList.get(taskNumber - 1);
//        UI.printMessageToConsole("Nice! I've marked this task as done:");
//        UI.printMessageToConsole(" " + this.tasksList.get(taskNumber - 1));
    }

    /**
     * Unmarks the task identified by its task number in the task list.
     * Does nothing if the task is already unmarked.
     *
     * @param taskNumber the task number in the task list
     * @throws TaskDoesNotExist if the task does not exist in the task list
     */
    public String unmarkTask(int taskNumber) throws TaskDoesNotExist {
        if (taskNumber <= 0 || taskNumber > this.tasksList.size()) {
            throw new TaskDoesNotExist();
        }

        this.tasksList.get(taskNumber - 1).unmark();

        return "OK, I've marked this task as not done yet: "
                + this.tasksList.get(taskNumber - 1);
//        UI.printMessageToConsole("OK, I've marked this task as not done yet:");
//        UI.printMessageToConsole(" " + this.tasksList.get(taskNumber - 1));
    }

    /**
     * Returns a list of tasks with the keyword given in their description.
     * If there is no matching task, returns an empty list.
     *
     * @param keyword the keyword in the description of tasks to find
     * @return the list of tasks with the keyword in their description
     */
    public List<Task> findTasksByDescriptionKeyword(String keyword) {
        List<Task> tasksWithKeyword = new ArrayList<>();

        for (Task task : this.tasksList) {
            if (task.getDescription().toLowerCase()
                    .contains(keyword.toLowerCase())) {
                tasksWithKeyword.add(task);
            }
        }

        return tasksWithKeyword;
    }
}
