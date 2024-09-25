package rei;

import java.util.ArrayList;
import java.util.List;

/**
 * This class encapsulates the list of tasks
 */
public class TaskList {
    private List<Task> listOfTasks;

    /**
     * Constructs a TaskList instance with an existing list of tasks
     * @param listOfTasks the list of tasks
     */
    public TaskList(List<Task> listOfTasks) {
        this.listOfTasks = listOfTasks;
    }

    public TaskList() {
        this.listOfTasks = new ArrayList<>();
    }


    /**
     * Returns the number of tasks on the list
     * @return the number of tasks on the list
     */
    public int getNumOfTasks() {
        return listOfTasks.size();
    }

    /**
     * Returns a Task instance on the list
     * @param taskIndex the Task index
     * @return the Task instance at the index position
     */
    public Task getTask(int taskIndex) {
        return listOfTasks.get(taskIndex);
    }

    /**
     * Adds a new Task instance to the list
     * @param newTask the new Task
     * @return the message after adding a Task to the list
     */
    public String addTask(Task newTask) {
        listOfTasks.add(newTask);
        return "Got it. I've added this task:\n" +
                "    " + this.getTask(this.getNumOfTasks() - 1) + "\n" +
                String.format("Now you have %d tasks in the list.", this.getNumOfTasks());
    }

    /**
     * Marks a Task as completed
     * @param taskIndex the Task index
     * @return the message, whether the process succeeds or fails
     */
    public String markTask(int taskIndex) {
        if (taskIndex > getNumOfTasks() || taskIndex <= 0) {
            return "No task found. Please retry!";
        }

        listOfTasks.get(taskIndex - 1).markAsDone();
        return "Okay! I've marked this task as done:\n" + "    " + getTask(taskIndex - 1);

    }

    /**
     * Unmarks a Task from being completed
     * @param taskIndex the Task index
     * @return the message, differs whether the process succeeds or fails
     */
    public String unmarkTask(int taskIndex) {
        if (taskIndex > getNumOfTasks() || taskIndex <= 0) {
            return "No task found. Please retry!";
        }

        listOfTasks.get(taskIndex - 1).markAsNotDone();
        return "Okay! I've marked this task as not done yet:\n" + getTask(taskIndex - 1);

    }

    /**
     * Deletes a Task from TaskList
     * @param taskIndex the index of the Task to be deleted
     * @return the message, differs whether the process succeeds or fails
     */
    public String deleteTask(int taskIndex) {
        if (taskIndex > getNumOfTasks() || taskIndex <= 0) {
            return "No task found. Please retry!";
        }

        Task removed = listOfTasks.remove(taskIndex - 1);
        return "Okay! I've deleted this task :\n" +
                removed + "\n" +
                String.format("Now you have %d tasks in the list.", getNumOfTasks());

    }


    /**
     * Finds the Tasks in TaskList containing a specific keyword
     * @param keyword
     * @return the message, differs whether the process succeeds or fails
     */
    public String findTasks(String keyword) {

        List<Task> filteredList = this.listOfTasks.stream().filter(task -> task.getTaskName().contains(keyword) || task.hasTag(keyword)).toList();
        String taskList = "";

        for (int i = 0; i < filteredList.size(); i++) {
            taskList += String.format("%d. %s\n", i + 1, filteredList.get(i).toString());
        }

        if (taskList.equals("")) {
            return "No matching tasks found on your list";
        }

        return "Here are the matching tasks in your list: \n" + taskList;
    }

    /**
     * Add a list of tags into a Task
     * @param taskIndex the Task index
     * @param tags the list of tags
     * @return the message, whether the process succeeds or fails
     */
    public String addTags(int taskIndex, List<String> tags) {
        if (taskIndex > getNumOfTasks() || taskIndex <= 0) {
            return "No task found. Please retry!";
        }

        listOfTasks.get(taskIndex - 1).addTags(tags);

        return "Okay! I've added the tags to this task:\n" + "    " + getTask(taskIndex - 1);
    }

    /**
     * Deletes a tag from a Task
     * @param taskIndex the Task index
     * @param tag the tag
     * @return the message, whether the process succeeds or fails
     */
    public String deleteTag(int taskIndex, String tag) {
        if (taskIndex > getNumOfTasks() || taskIndex <= 0) {
            return "No task found. Please retry!";
        }

        listOfTasks.get(taskIndex - 1).deleteTag(tag);

        return "Okay! I've deleted the tag from this task:\n" + "    " + getTask(taskIndex - 1);
    }

    /**
     * Shows all the tags assigned to a Task
     * @param taskIndex the Task index
     * @return the tags in String representation
     */
    public String viewTags(int taskIndex) {
        if (taskIndex > getNumOfTasks() || taskIndex <= 0) {
            return "No task found. Please retry!";
        } else if (listOfTasks.get(taskIndex - 1).allTagsToString().equals("")) {
            return "No tags found.";
        }

        return "tags:" + listOfTasks.get(taskIndex - 1).allTagsToString();
    }

    /**
     * Returns the list of Tasks in String
     * @return the message and the list of Tasks in String
     */
    @Override
    public String toString() {
        String taskList = "";
        for (int i = 0; i < listOfTasks.size(); i++) {
            taskList += String.format("%d. %s\n", i + 1, listOfTasks.get(i).toString());
        }
        return "Here are the tasks in your list: \n" + taskList;
    }

    /**
     * Converts the list of Tasks into its storing format in String
     * @return the String format for storing the tasks
     */
    public String toStoringFormat() {
        String tasksInStoringFormat = "";
        for (int i = 0; i < this.getNumOfTasks(); i++) {
            tasksInStoringFormat += this.getTask(i).toStoringFormat() + "\n";
        }
        return tasksInStoringFormat;
    }

}
