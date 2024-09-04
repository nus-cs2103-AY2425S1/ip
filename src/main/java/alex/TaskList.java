package alex;

import java.util.ArrayList;

import java.io.IOException;

import alex.task.Task;

/**
 * Encapsulates the list of tasks and contains operations to modify the list such as add and delete tasks.
 */
public class TaskList {
    private ArrayList<Task> list;
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Returns the current number of Tasks in the list.
     */
    public int getSize() {
        return this.list.size();
    }

    /**
     * Adds a Task to the Task list and saves to file.
     *
     * @param task Task object to be added.
     * @param storage Storage object to help with saving changes of the Tasklist to file.
     * @throws IOException If there are issues saving the Tasklist to file.
     */
    public void add(Task task, Storage storage) throws IOException {
        this.list.add(task);
        storage.save(list);
    }

    /**
     * Deletes a Task from Task list, saves changes to file and informs user that the Task
     * has been deleted.
     *
     * @param taskNumber Number representing the Task in the Task list to be deleted.
     * @param storage Storage object to help save changes to file.
     * @param ui Ui object to send a message to user that the Task has been deleted.
     * @throws IOException If there are issues when saving changes to file.
     */
    public String delete(int taskNumber, Storage storage, Ui ui) throws IOException {
        Task task = list.get(taskNumber - 1);
        list.remove(taskNumber - 1);
        storage.save(list);
        return ui.showMessage("Noted. I've removed this task: ", task, this.list.size());
    }

    /**
     * Marks a Task as done, saves changes and informs user.
     *
     * @param taskNumber Number representing the Task to be marked.
     * @param storage Storage object to save changes to file.
     * @param ui Ui object to inform user that the Task has been marked as completed.
     * @throws IOException If there are issues saving changes to file.
     */
    public String mark(int taskNumber, Storage storage, Ui ui) throws IOException {
        Task task = list.get(taskNumber - 1);
        task.markAsDone();
        storage.save(list);
        return ui.showMark(task);
    }

    /**
     * Mark a Task as undone, save changes to file and informs user.
     *
     * @param taskNumber Number representing Task to unmark.
     * @param storage Storage object to save changes to file.
     * @param ui Ui object to inform user that the Task has been unmarked.
     * @throws IOException If there are issues saving changes to file.
     */
    public String unmark(int taskNumber, Storage storage, Ui ui) throws IOException {
        Task task = list.get(taskNumber - 1);
        task.markAsUndone();
        storage.save(list);
        return ui.showUnmark(task);
    }

    /**
     * Displays the list of Tasks that the user currently has.
     */
    public String showTasks(String message) {
        StringBuilder sb = new StringBuilder();
        sb.append(message);
        sb.append("\n");
        for (int i = 1; i <= this.list.size(); i++) {
            sb.append(i);
            sb.append(". ");
            sb.append(this.list.get(i - 1));
            sb.append("\n");
        }
        return sb.toString();
    }

    public String findWord(String string, Ui ui) {
        ArrayList<Task> list = new ArrayList<>();
        for (int i = 0; i < this.list.size(); i++) {
            Task task = this.list.get(i);
            if (task.hasSearchString(string)) {
                list.add(task);
            }
        }
        return ui.showTasks(new TaskList(list), "\nHere are the matching tasks in your list: ");
    }
}
