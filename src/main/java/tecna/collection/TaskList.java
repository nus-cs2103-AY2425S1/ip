package tecna.collection;

import java.util.ArrayList;

import tecna.exception.TaskDuplicateException;
import tecna.task.Task;

public class TaskList {
    private ArrayList<Task> tasks;
    private int size;

    public TaskList() {
        this.tasks = new ArrayList<>();
        this.size = 0;
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.size = tasks.size();
    }

    public int getSize() {
        return size;
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Displays all the tasks in the task list
     */
    public String listItems() {
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < this.size; ++i) {
            sb.append(i + 1 + ". " + this.tasks.get(i) + "\n");
        }
        String response = sb.toString();
        System.out.println(response);
        return response;
    }

    /**
     * Adds new item to the list of tasks
     *
     * @param task extracted from the user input
     */
    public String addItem(Task task) throws TaskDuplicateException {
        assert task != null;

        if (isDuplicate(task)) {
            throw new TaskDuplicateException();
        }

        this.tasks.add(task);
        ++this.size;
        StringBuilder sb = new StringBuilder("Sure! I've added this tasks:\n");
        sb.append(task + "\n");
        sb.append(">> Now you have " + this.size + (size > 1 ? " tasks" : " task") + " in the list.\n");
        String response = sb.toString();
        System.out.println(response);
        return response;
    }

    public boolean isDuplicate(Task task) {
        for (Task t : this.tasks) {
            if (task.equals(t)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Deletes the specified items.
     *
     * @param index
     */
    public String deleteItem(int index) {
        String item = this.tasks.get(index).toString();
        this.tasks.remove(index);
        --this.size;
        StringBuilder sb= new StringBuilder("Sure! I've deleted this task:");
        sb.append(item);
        sb.append(">> Now you have " + this.size + (size > 1 ? " tasks" : " task") + " in the list.");
        String response = sb.toString();
        System.out.println(response);
        return response;
    }

    /**
     * Marks the index-th task as done.
     *
     * @param index
     */
    public void mark(int index) {
        this.tasks.get(index).markAsDone();
    }

    /**
     * Unmarks the index-th task.
     *
     * @param index
     */
    public void unmark(int index) {
        this.tasks.get(index).unMarkAsDone();
    }

    /**
     * Finds and prints all the matching tasks.
     *
     * @param keyword is one word that user enters to filter the task.
     */
    public String findTasks(String keyword) {
        assert !keyword.isEmpty(); // handled by CommandScanner
        int counter = 0;
        StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:\n");
        for (Task task : this.tasks) {
            String[] words = task.getTaskName().split(" ");

            for (String word : words) {
                if (word.toLowerCase().contains(keyword.toLowerCase())) {
                    counter++;
                    sb.append(counter + ". " + task + "\n");
                    break;
                }
            }
        }
        String response = sb.toString();
        System.out.println(response);
        return response;
    }
}


