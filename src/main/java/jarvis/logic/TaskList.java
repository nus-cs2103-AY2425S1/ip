package jarvis.logic;

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
    public String list() {
        String result = "";
        for (int i = 0; i < list.size(); i++) {
            Task task = this.list.get(i);
            String newTask = String.format("%d. %s\n", i + 1, task);
            result += newTask;
        }
        return "here are your tasks: \n" + result;
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
    public String add(String input) throws IllegalArgumentException {

        String[] words = input.split(" ");
        String[] parts = input.split(" /");
        switch (words[0]) {
            case "todo" -> {
                String nameAndType = parts[0].substring(5);
                Task todo = new Todo(nameAndType);
                list.add(todo);
                storage.add(todo.toString());
                return acknowledge();
            }
            case "event" -> {
                String nameAndType = parts[0].substring(6);
                String from = parts[1].replace("from ", "");
                String to = parts[2].replace("to ", "");
                Task event = new Event(nameAndType, from, to);
                list.add(event);
                storage.add(event.toString());
                return acknowledge();
            }
            case "deadline" -> {
                String nameAndType = parts[0].substring(9);
                String end = parts[1].replace("by ", "by: ");
                Task deadline = new Deadline(nameAndType, end);
                list.add(deadline);
                storage.add(deadline.toString());
                return acknowledge();
            }
            default -> {
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
            storage.unmark(i - 1);
    }

    // Generates the message for the unmark dialog
    public String unmarkDialog(int i) {
        boolean validIndex = i - 1 < 0 || i > this.getNumTasks();
        if(validIndex){
            return "index error!";
        }
        return String.format("Nice! I've marked this task as not done:\n" +
                "  [ ] %s\n" , this.list.get(i - 1).getName());
    }

    /**
     * Marks the task at the specified index as done.
     *
     * @param i the index of the task to mark.
     */
    public void mark(int i) {
        this.list.get(i - 1).setDone(true);
        storage.mark(i - 1);
    }

    // Generates the message for the mark dialog
    public String markDialog(int i) {
        boolean validIndex = i - 1 < 0 || i > this.getNumTasks();
        if(validIndex){
            return "index error!";
        }
        return String.format(
                "Nice! I've marked this task as done:\n" +
                "  [X] %s\n" , this.list.get(i - 1).getName());
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
        this.list.remove(i - 1);
        storage.delete(i - 1);
    }

    // Generates the message for the delete dialog
    public String deleteDialog(int i) {
        boolean validIndex = i - 1 < 0 || i > this.getNumTasks();
        if(validIndex){
            return "index error!";
        }
        return String.format("Noted. I've removed this task:\n" +
                " %s\nNow you have %d tasks in the list.\n" , this.list.get(i - 1),
                this.list.size());
    }

    /**
     * Finds tasks in the TaskList that match the given search string.
     *
     * @param toFind the search string.
     */
    public String find(String toFind) {
        int counter = 0;
        StringBuilder result = new StringBuilder("");
        result.append("Here are the tasks matching \"").append(toFind).append("\":\n");

        for (int i = 0; i < this.list.size(); i++) {
            if (this.list.get(i).getName().trim().equalsIgnoreCase(toFind.trim())) {
                counter++;
                result.append(this.list.get(i).toString()).append("\n");
            }
        }

        if (counter == 0) {
            result.append("No such task ").append(toFind).append("\n");
        }

        return result.toString();
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