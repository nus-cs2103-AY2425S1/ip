package gojou.task;

import java.io.IOException;
import java.util.ArrayList;

import gojou.Storage;
import gojou.Ui;

/**
 * Encapsulates the list of tasks and contains operations to modify the list,
 * such as adding and deleting tasks.
 */
public class TaskList {
    private ArrayList<Task> list;

    /**
     * Constructs a TaskList with the given list of tasks.
     *
     * @param list The initial list of tasks.
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Returns the current number of tasks in the list.
     *
     * @return The size of the task list.
     */
    public int getSize() {
        return this.list.size();
    }

    /**
     * Adds a Task to the Task list and saves the changes to the file.
     *
     * @param task The Task object to be added.
     * @param storage The Storage object to help with saving changes to the file.
     * @throws IOException If there are issues saving the TaskList to the file.
     */
    public void add(Task task, Storage storage) throws IOException {
        this.list.add(task);
        this.list.sort(Task::compareTo);
        storage.save(list);
    }

    /**
     * Deletes a Task from the Task list, saves changes to the file, and informs the user
     * that the Task has been deleted.
     *
     * @param taskNumber The number representing the Task in the Task list to be deleted.
     * @param storage The Storage object to help save changes to the file.
     * @param ui The Ui object to send a message to the user that the Task has been deleted.
     * @return A message informing the user that the Task has been removed.
     * @throws IOException If there are issues saving changes to the file.
     */
    public String delete(int taskNumber, Storage storage, Ui ui) throws IOException {
        Task task = list.get(taskNumber - 1);
        list.remove(taskNumber - 1);
        storage.save(list);
        return ui.showMessage("Noted. I've removed this task: ", task, this.list.size());
    }

    /**
     * Marks a Task as done, saves changes, and informs the user.
     *
     * @param taskNumber The number representing the Task to be marked.
     * @param storage The Storage object to save changes to the file.
     * @param ui The Ui object to inform the user that the Task has been marked as completed.
     * @return A message informing the user that the Task has been marked as completed.
     * @throws IOException If there are issues saving changes to the file.
     */
    public String mark(int taskNumber, Storage storage, Ui ui) throws IOException {
        Task task = list.get(taskNumber - 1);
        task.markAsDone();
        storage.save(list);
        return ui.showMark(task);
    }

    /**
     * Marks a Task as undone, saves changes to the file, and informs the user.
     *
     * @param taskNumber The number representing the Task to be unmarked.
     * @param storage The Storage object to save changes to the file.
     * @param ui The Ui object to inform the user that the Task has been unmarked.
     * @return A message informing the user that the Task has been unmarked.
     * @throws IOException If there are issues saving changes to the file.
     */
    public String unmark(int taskNumber, Storage storage, Ui ui) throws IOException {
        Task task = list.get(taskNumber - 1);
        task.markAsUndone();
        storage.save(list);
        return ui.showUnmark(task);
    }

    /**
     * Displays the list of tasks that the user currently has.
     *
     * @param message A message to be displayed before the list of tasks.
     * @return A String representation of the list of tasks.
     */
    public String showTasks(String message) {
        assert this.list != null : "tasklist is null";
        StringBuilder sb = new StringBuilder();
        sb.append(message);
        sb.append("\n");
        for (int i = 1; i <= this.list.size(); i++) {
            sb.append(i);
            sb.append(". ");
            sb.append(this.list.get(i - 1));
            if (i != this.list.size()) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    /**
     * Finds tasks containing the specified search string and displays them.
     *
     * @param string The string to search for within the task descriptions.
     * @param ui The Ui object to display the matching tasks.
     * @return A message with the matching tasks.
     */
    public String findWord(String string, Ui ui) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : this.list) {
            if (task.checkHaveSearchString(string)) {
                matchingTasks.add(task);
            }
        }
        return ui.showTasks(new TaskList(matchingTasks), "Here are the matching tasks in your list: ");
    }
}

