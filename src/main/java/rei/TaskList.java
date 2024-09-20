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



    /**
     * Returns the number of tasks on the list
     * @return the number of tasks on the list
     */
    public int getNumOfTasks() {
        return listOfTasks.size();
    }

    /**
     * Returns a Task instance on the list
     * @param index the Task index
     * @return the Task instance at the index position
     */
    public Task getTask(int index) {
        return listOfTasks.get(index);
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
     * @param index the Task index
     * @return the message, whether the process succeeds or fails
     */
    public String markTask(int index) {
        if (index <= getNumOfTasks() && index > 0) {
            listOfTasks.get(index - 1).markAsDone();
            return "Okay! I've marked this task as done:\n" + "    " + getTask(index - 1);
        } else {
            return "No task found. Please retry!";
        }
    }

    /**
     * Unmarks a Task from being completed
     * @param index the Task index
     * @return the message, differs whether the process succeeds or fails
     */
    public String unmarkTask(int index) {
        if (index <= getNumOfTasks() && index > 0) {
            listOfTasks.get(index - 1).markAsNotDone();
            return "Okay! I've marked this task as not done yet:\n" + getTask(index - 1);
        } else {
            return "No task found. Please retry!";
        }
    }

    /**
     * Deletes a Task from TaskList
     * @param index the index of the Task to be deleted
     * @return the message, differs whether the process succeeds or fails
     */
    public String deleteTask(int index) {
        if (index <= getNumOfTasks() && index > 0) {
            Task removed = listOfTasks.remove(index - 1);
            return "Okay! I've deleted this task :\n" +
                    removed + "\n" +
                    String.format("Now you have %d tasks in the list.", getNumOfTasks());
        } else {
            return "No task found. Please retry!";
        }
    }


    /**
     * Finds the Tasks in TaskList containing a specific keyword
     * @param keyword
     * @return the message, differs whether the process succeeds or fails
     */
    public String findTasks(String keyword) {
        List<Task> filteredList = this.listOfTasks.stream().filter(task -> task.getTaskName().contains(keyword)).toList();
        String taskList = "";

        for (int i = 0; i < filteredList.size(); i++) {
            taskList += String.format("%d. %s\n", i + 1, filteredList.get(i).toString());
        }

        if (taskList.equals("")) {
            return "No matching tasks found on your list";
        } else {
            return "Here are the matching tasks in your list: \n" + taskList;
        }
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
