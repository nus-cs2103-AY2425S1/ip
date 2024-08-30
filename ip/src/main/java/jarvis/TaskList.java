package jarvis;

import java.util.ArrayList;

/**
 * Represents a list of tasks with methods to add, delete, and manage tasks.
 */
public class TaskList {

    // Private fields with accessor methods
    private ArrayList<Task> list;
    private Storage storage = Storage.getInstance();

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        list = new ArrayList<>();
    }

    /**
     * Lists all the tasks in the TaskList.
     */
    public void list() {
        for (int i = 0; i < list.size(); i++) {
            Task task = this.list.get(i);
            String checkbox = task.isDone() ? "[X]" : "[ ]";
            System.out.printf("%d. %s\n", i + 1, task);
        }
    }

    /**
     * Returns the number of tasks in the TaskList.
     *
     * @return the number of tasks.
     */
    public int getNumTasks() {
        return this.list.size();
    }

    /**
     * Adds a task to the TaskList based on the given input.
     *
     * @param input the input string representing the task.
     * @throws IllegalArgumentException if the input is invalid.
     */
    public void add(String input) throws IllegalArgumentException {

        String[] words = input.split(" ");
        String[] parts = input.split(" /");
        switch (words[0]) {
            case "todo" -> {
                String nameAndType = parts[0].substring(5);
                Task todo = new Todo(nameAndType);
                list.add(todo);
                storage.add(todo.toString());
            }
            case "event" -> {
                String nameAndType = parts[0].substring(6);
                String from = parts[1].replace("from ", "");
                String to = parts[2].replace("to ", "");
                Task event = new Event(nameAndType, from, to);
                list.add(event);
                storage.add(event.toString());
            }
            case "deadline" -> {
                String nameAndType = parts[0].substring(9);
                String end = parts[1].replace("by ", "by: ");
                Task deadline = new Deadline(nameAndType, end);
                list.add(deadline);
                storage.add(deadline.toString());
            }
            default -> {
                System.out.println("Unknown command");
                throw new IllegalArgumentException();
            }
        }
    }

    /**
     * Marks the task at the specified index as not done.
     *
     * @param i the index of the task to unmark.
     */
    public void unmark(int i) {
        this.list.get(i - 1).setDone(false);
        System.out.println("____________________________________________________________");
        System.out.println("Nice! I've marked this task as not done:");
        System.out.printf("  [ ] %s\n", this.list.get(i - 1).getName());
        System.out.println("____________________________________________________________");
        storage.unmark(i - 1);
    }

    /**
     * Marks the task at the specified index as done.
     *
     * @param i the index of the task to mark.
     */
    public void mark(int i) {
        this.list.get(i - 1).setDone(true);
        System.out.println("____________________________________________________________");
        System.out.println("Nice! I've marked this task as done:");
        System.out.printf("  [X] %s\n", this.list.get(i - 1).getName());
        System.out.println("____________________________________________________________");
        storage.mark(i - 1);
    }

    /**
     * Acknowledges the addition of a task to the TaskList.
     *
     * @return a message acknowledging the addition.
     */
    public String acknowledge() {
        Task task = this.list.get(this.list.size() - 1);
        String end = String.format("\nNow you have %d task(s) in the list", this.list.size());
        String result = "Got it. I've added this task:\n" + task.toString() + end;
        return result;
    }

    public void handleDelete(int i) {
        System.out.println("____________________________________________________________");
        System.out.println("Noted. I've removed this task:");
        System.out.printf(" %s", this.list.get(i - 1));
        System.out.printf("Now you have %d tasks in the list.\n", this.list.size() - 1);
        System.out.println("____________________________________________________________");
        this.list.remove(i - 1);
        storage.delete(i - 1);
    }

    /**
     * Finds tasks in the TaskList that match the given search string.
     *
     * @param toFind the search string.
     */
    public void find(String toFind) {
        int counter = 0;
        System.out.println("____________________________________________________________");
        StringBuilder result = new StringBuilder("Here are the tasks matching \"" + toFind + "\":\n");
        for (int i = 0; i < this.list.size(); i++) {
            if (this.list.get(i).getName().trim().equals(toFind.trim())) {
                counter++;
                result.append(this.list.get(i).toString()).append("\n");
            }
        }
        if (counter != 0) {
            System.out.println(result);
        } else {
            System.out.println("No such task " + toFind);
        }
        System.out.println("____________________________________________________________");
    }

    // Getter methods for private fields

    /**
     * Returns the list of tasks.
     *
     * @return the list of tasks.
     */
    public ArrayList<Task> getList() {
        return list;
    }

    /**
     * Returns the storage instance.
     *
     * @return the storage instance.
     */
    public Storage getStorage() {
        return storage;
    }
}