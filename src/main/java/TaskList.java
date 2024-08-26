import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Adds a task to the list and increments the task count.
     *
     * @param task The task to be added to the list.
     */
    public void addToList(Task task) {
        this.list.add(task);
        Ui.printTaskAdded(task, this.list.size());
    }

    /**
     * Deletes a task from the list at the specified index and decrements the task count.
     *
     * @param index The index of the task to be removed from the list.
     * @throws IndexOutOfBoundsException If the index is out of range (index < 0 || index >= size()).
     */
    public void deleteFromList(int index) {
        try {
            Task task = this.list.get(index);
            this.list.remove(index);
            Ui.printTaskDeleted(task, index);
        } catch (IndexOutOfBoundsException err) {
            Ui.printNoSuchTaskErr(index);
        }
    }

    public void markTaskAsDone(int index) {
        try {
            Task task = this.list.get(index);
            task.markAsDone();
            Ui.printMarkedAsDone(task);
        } catch (IndexOutOfBoundsException err) {
            Ui.printNoSuchTaskErr(index);
        }
    }

    public void unmarkTaskAsDone(int index) {
        try {
            Task task = this.list.get(index);
            task.unmarkAsDone();
            Ui.printUnmarkedAsDone(task);
        } catch (IndexOutOfBoundsException err) {
            Ui.printNoSuchTaskErr(index);
        }
    }

    /**
     * Prints all tasks in the list. Prints error message if the list is empty.
     */
    public void printList() {
        if (this.list.size() == 0) {
            Ui.printEmptyListErr();
            return;
        }
        System.out.println("Here are the tasks in your list:");
        int count = 1;
        for (Task task : this.list) {
            if (task == null) break;
            System.out.println(count++ + "." + task);
        }
    }

    public String toFileData() {
        StringBuilder fileData = new StringBuilder();
        for (Task task : list) {
            fileData.append(task.toFileEntry() + System.lineSeparator());
        }
        return fileData.toString();
    }
}
