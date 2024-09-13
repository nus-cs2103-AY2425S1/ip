package toothless.task;

import java.util.ArrayList;

import toothless.exceptions.ToothlessExceptions;
import toothless.ui.Ui;


/**
 * TaskList represents a list of tasks that is stored in the chat application.
 */
public class TaskList {
    private ArrayList<Task> list;

    /**
     * Constructor for TaskList.
     *
     * @param list The list of tasks.
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Returns the list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getList() {
        return list;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task     The task to be added.
     * @param ui       The user interface.
     * @param taskList The task list.
     * @return The message to be displayed after adding the task.
     */
    public String addTask(Task task, Ui ui, TaskList taskList) {
        list.add(task);
        return ui.addTaskMessage(task, taskList.getList().size());
    }

    /**
     * Prints the tasks in the task list.
     *
     * @return The tasks in the task list.
     */
    public String printTask() {
        StringBuilder response = new StringBuilder("""
                Here are the tasks on the quest board:

                |-------------Quest Board -----------------|
                """);

        if (list.isEmpty()) {
            return "There are no quests on the quest board yet!";
        } else {
            for (int i = 0; i < list.size(); i++) {
                response.append(String.format("%d. %s\n", i + 1, list.get(i).toString()));
            }
        }

        response.append("|-------------------------------------------|\n\n");
        return response.toString();
    }

    /**
     * Marks a task as done.
     *
     * @param index The index of the task to be marked as done.
     * @return The message to be displayed after marking the task as done.
     * @throws ToothlessExceptions If the index is out of range.
     */
    public String markDone(int index, Ui ui) throws ToothlessExceptions {
        if (index > list.size() || index < 1) {
            throw new ToothlessExceptions("The index is out of range! Please enter a valid index.\n\n");
        }
        int fixedIndex = index - 1;
        Task currentTask = list.get(fixedIndex);
        currentTask.markAsDone();
        return ui.markDoneMessage(currentTask);
    }

    /**
     * Mark a task as undone.
     *
     * @param index The index of the task to be marked as undone.
     * @return The message to be displayed after marking the task as undone.
     * @throws ToothlessExceptions If the index is out of range.
     */
    public String markUndone(int index, Ui ui) throws ToothlessExceptions {
        if (index > list.size() || index < 1) {
            throw new ToothlessExceptions("The index is out of range! Please enter a valid index.\n\n");
        }
        int fixedIndex = index - 1;
        Task currentTask = list.get(fixedIndex);
        currentTask.markAsUndone();
        return ui.markUndoneMessage(currentTask);
    }

    /**
     * Deletes a task from the task list.
     *
     * @param index The index of the task to be deleted.
     * @return The message to be displayed after deleting the task.
     * @throws ToothlessExceptions If the index is out of range.
     */
    public String deleteTask(int index) throws ToothlessExceptions {
        if (index > list.size() || index < 1) {
            throw new ToothlessExceptions("The index is out of range! Please enter a valid index.\n\n");
        }
        int fixedIndex = index - 1;
        Task currentTask = list.get(fixedIndex);
        int newTaskSize = list.size() - 1;
        list.remove(fixedIndex);

        return "The quest\n\t\t"
                + currentTask + "\nis removed from the quest board!\n\n"
                + "Now there is " + newTaskSize + " quests in your quest board.\n\n";
    }

    /**
     * Finds a task that matches the keyword.
     *
     * @param keyword The keyword to be searched.
     * @return The message to be displayed after finding the task.
     */
    public String findTask(String... keyword) {
        StringBuilder response = new StringBuilder("Here are the quests that match your keyword:\n");
        int taskCount = 0;
        for (Task task : list) {
            for (String key : keyword) {
                String[] splitKey = key.split("\\s+");
                for (String k : splitKey) {
                    if (task.getDescription().contains(k)) {
                        response.append(String.format("%d. %s\n", list.indexOf(task) + 1, task));
                        taskCount++;
                        break;
                    }
                }
            }
        }

        if (taskCount == 0) {
            return "Oopsie! Seems like there are no quests that match your keyword!\n\n";
        }

        return response.toString();
    }
}
