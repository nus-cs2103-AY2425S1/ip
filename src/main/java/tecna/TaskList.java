package tecna;

import java.util.ArrayList;

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
    public void listItems() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < this.size; ++i) {
            System.out.println(i + 1 + ". " + this.tasks.get(i));
        }
    }

    /**
     * Adds new item to the list of tasks
     * @param item extracted from the user input
     */
    public void addItem(Task item) {
       Task task = item;
       this.tasks.add(task);
       ++this.size;
        System.out.println("Sure! I've added this task:");
        System.out.println(task);
        System.out.println(">> Now you have " + this.size + (size > 1 ? " tasks" : " task") + " in the list." );
    }

    /**
     * Deletes the specified items.
     * @param index
     */
    public void deleteItem(int index) {
            String item = this.tasks.get(index).toString();
            this.tasks.remove(index);
            --this.size;
            System.out.println("Sure! I've deleted this task:");
            System.out.println(item);
            System.out.println(">> Now you have " + this.size + (size > 1 ? " tasks" : " task") + " in the list." );
    }

    /**
     * Marks the index th task as done.
     * @param index
     */
    public void mark(int index) {
        this.tasks.get(index).markAsDone();
    }

    /**
     * Unmarks the index the task.
     * @param index
     */
    public void unmark(int index) {
        this.tasks.get(index).unMarkAsDone();
    }
}


