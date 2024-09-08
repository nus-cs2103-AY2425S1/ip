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
     */
    public String addTask(Task task, Ui ui, TaskList taskList) {
        list.add(task);
        return ui.addTaskMessage(task, taskList.getList().size());
    }

    /**
     * Prints the tasks in the task list.
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
     */
    public String findTask(String keyword) {
        StringBuilder response = new StringBuilder("Here are the quests that match your keyword:\n");
        int taskCount = 0;
        for (Task task : list) {
            if (task.getDescription().contains(keyword)) {
                response.append(String.format("%d. %s\n", list.indexOf(task) + 1, task));
                taskCount++;
            }
        }

        if (taskCount == 0) {
            return "Oopsie! Seems like there are no quests that match your keyword!\n\n";
        }

        return response.toString();
    }
}
