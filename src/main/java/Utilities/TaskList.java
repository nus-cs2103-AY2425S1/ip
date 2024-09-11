package utilities;

import java.util.ArrayList;

import tasks.Task;

public class TaskList {
    private final static String MESSAGE_ONFIND = "I found some matching tasks in your list: ";

    private ArrayList<Task> taskList;

    public TaskList(Storage s) {
        this.taskList = s.loadTaskListFromFile();
    }

    /**
     * Adds new task into the TaskList.
     *
     * @param t Task to be added.
     * @param name Name of the task to be added.
     * @return String of user response
     */
    public String addToTaskList(Task t, String name) {
        String message = "";

        this.taskList.add(t);
        message += Ui.updateUserOnAddition(name, this.taskList.size());

        return message;
    }

    /**
     * Removes task from the TaskList.
     *
     * @param index Index of task to be removed.
     * @return String of user response
     */
    public String removeFromTaskList(int index) {
        String message = "";

        Task t = this.taskList.get(index);
        this.taskList.remove(index);
        message += Ui.updateUserOnDeletion(t);

        return message;
    }

    /**
     * Updates task status in the TaskList.
     *
     * @param index Index of task to be updated.
     * @param status Boolean status to update.
     * @return String of user response
     */
    public String updateTaskListStatus(int index, boolean status) {
        String message = "";

        Task t = this.taskList.get(index);
        if (status) {
            t.markAsDone();
            message += Ui.updateUserOnCompletion(t);
        } else {
            t.markAsNotDone();
            message += Ui.updateUserOnUncompletion(t);
        }

        return message;
    }

    /**
     * Updates the tag of the task.
     * If the task is already tagged, the previous tag is overwritten.
     *
     * @param index Index of task to be updated.
     * @param tag String tag to be applied.
     * @return String of user response
     */
    public String addTag(int index, String tag) {
        String message = "";

        Task t = this.taskList.get(index);
        t.setTag(tag);
        message += Ui.updateUserOnTag(t);

        return message;
    }

    /**
     * Finds and prints matching tasks for given keyword.
     * Allows matches via the tag for the task as well.
     *
     * @param input String pattern to be matched for search.
     * @return String of user response
     */
    public String findTasks(String input) {
        String message = "";

        message += MESSAGE_ONFIND;
        int count = 1;
        for (Task t : this.taskList) {
            if (t.toString().contains(input)) {
                message += String.format("%d.%s", count, t.toString());
                count++;
            }
        }

        return message;
    }

    public int getSize() {
        return this.taskList.size();
    }

    @Override
    public String toString() {
        String output = "";
        int count = 1;
        for (Task t : this.taskList) {
            String temp = String.format("%d.%s  %s\n", count, t.toString(), t.getTag());
            output += temp;
            count++;
        }

        return output;
    }
}
