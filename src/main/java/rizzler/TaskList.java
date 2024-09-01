package rizzler;

import java.util.ArrayList;

/**
 * Represents the list of tasks that Rizzler is managing.
 */
class TaskList {
    private ArrayList<Task> tasks;
    private Ui ui;

    /**
     * Constructs a new <code>TaskList</code> with
     * the supplied ArrayList of tasks.
     *
     * @param tasks ArrayList of tasks supplied by file storage.
     */
    TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.ui = new Ui();
    }

    /**
     * Adds a task to the underlying ArrayList
     * and commands Ui to output the completion message.
     *
     * @param task Task to be added.
     * @return String showing task addition.
     */
    String add(Task task) {
        this.tasks.add(task);
        return this.ui.showAdditionToList(task, this.tasks.size());
    }

    /**
     * Deletes a task from the underlying ArrayList
     * as indicated by the index and commands Ui to
     * output the completion message.
     *
     * @param index index of the specified task in the list.
     * @throws RizzlerException If list is empty or index is not in the list.
     */
    String delete(int index) throws RizzlerException {
        if (this.tasks.isEmpty()) {
            throw new RizzlerException("No tasks to delete");
        }
        try {
            Task removedTask = this.tasks.remove(index);
            return this.ui.showRemovalFromList(
                    removedTask,
                    this.tasks.size());
        } catch (IndexOutOfBoundsException e) {
            throw new RizzlerException(
                    "Please put task number that is actually in the list");
        }
    }

    /**
     * Uses the Ui to output the String representation
     * of tasks in the list.
     *
     * @return String detailing list of tasks.
     */
    String list() {
        return this.ui.showList(this.tasks);
    }

    /**
     * Returns the underlying ArrayList of tasks specifically
     * to be passed to the file storage to be saved.
     *
     * @return ArrayList of tasks in <code>TaskList</code>.
     */
    ArrayList<Task> getListToSave() {
        return this.tasks;
    }

    /**
     * Marks a task in the underlying ArrayList as done
     * as indicated by the index then commands Ui to
     * output a completion message.
     *
     * @param index index of specified task in the list.
     * @return String detailing the marked task.
     * @throws RizzlerException If list is empty or index is not in the list.
     */
    String mark(int index) throws RizzlerException {
        if (this.tasks.isEmpty()) {
            throw new RizzlerException("No tasks to mark");
        }
        try {
            this.tasks.get(index).markAsDone();
            return this.ui.showMarking(this.tasks.get(index));
        } catch (IndexOutOfBoundsException e) {
            throw new RizzlerException(
                    "Please put task number that is actually in the list");
        }
    }

    /**
     * Marks a task in the underlying ArrayList as not done
     * as indicated by the index then commands Ui to
     * output a completion message.
     *
     * @param index index of specified task in the list.
     * @return String detailing unmarked task.
     * @throws RizzlerException If list is empty or index is not in the list.
     */
    String unmark(int index) throws RizzlerException {
        if (this.tasks.isEmpty()) {
            throw new RizzlerException("No tasks to unmark");
        }
        try {
            this.tasks.get(index).unmark();
            return this.ui.showUnmarking(this.tasks.get(index));
        } catch (IndexOutOfBoundsException e) {
            throw new RizzlerException(
                    "Please put task number that is actually in the list");
        }
    }

    /**
     * Conducts a search among the tasklist for tasks with
     * name having a keyword in them, then adding the tasks to
     * another ArrayList, which is fed to the Ui to be outputted.
     * 
     * @param keyword Keyword to match with the task.
     * @return String detailing all found tasks.
     * @throws RizzlerException If the tasklist is empty.
     */
    String find(String keyword) throws RizzlerException {
        if (this.tasks.size() == 0) {
            throw new RizzlerException("No tasks here to find");
        }
        ArrayList<Task> foundList = new ArrayList<>();
        for (int i = 0; i < this.tasks.size(); i++) {
            if (this.tasks.get(i).matches(keyword)) {
                foundList.add(this.tasks.get(i));
            }
        }
        return this.ui.showFoundList(foundList);
    }
}
