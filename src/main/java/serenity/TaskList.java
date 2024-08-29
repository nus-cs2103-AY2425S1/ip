package serenity;

import java.util.ArrayList;

/**
 * Represents a list of tasks, where tasks can be added, deleted
 * and marked as done or undone.
 */
public class TaskList {

    protected ArrayList<Task> tasks;

    /**
     * Constructs a TaskList with no existing tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with existing tasks.
     *
     * @param tasks ArrayList of existing tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Creates task of specified type with given task description.
     *
     * @param input Task description specifying type of task.
     * @return Task of specified type with given task description
     * @throws SerenityException If type of task is not specified.
     */
    public Task createTask(String input) throws SerenityException {
        Task t;

        if (input.startsWith("todo")) {
            String[] description = input.split(" ");
            if (description.length == 1) {
                throw new SerenityException("Error: The description of a todo cannot be empty.");
            } else {
                //remove the type of task
                String taskDescription = input.split(" ", 2)[1];
                t = new Todo(taskDescription);
            }
        } else if (input.startsWith("deadline")) {
            String[] description = input.split(" ");
            if (description.length == 1) {
                throw new SerenityException("Error: The description of a deadline cannot be empty.");
            } else {
                String taskDescription = input.split(" ", 2)[1];
                String[] parts = taskDescription.split("/by");
                t = new Deadline(parts[0].strip(), parts[1].strip());
            }
        } else if (input.startsWith("event")) {
            String[] description = input.split(" ");
            if (description.length == 1) {
                throw new SerenityException("Error: The description of an event cannot be empty.");
            } else {
                String taskDescription = input.split(" ", 2)[1];
                String[] parts = taskDescription.split("/from");
                String[] timings = parts[1].split("/to");
                t = new Event(parts[0].strip(), timings[0].strip(), timings[1].strip());
            }
        } else {
            throw new SerenityException("Error: Type of task is not specified.");
        }

        return t;

    }

    /**
     * Adds task to TaskList.
     *
     * @param t Task to be added.
     * @return Message to be displayed when task is added.
     */
    public String addTask(Task t) {
        tasks.add(t);
        String numOfTasks = tasks.size() == 1 ? "task" : "tasks";
        return ("Got it. I've added this task:\n" + t
                + "\nNow you have " + tasks.size() + " " + numOfTasks + " in the list.");
    }

    /**
     * Deletes task from TaskList.
     *
     * @param input Command containing index of task to delete.
     * @return Message to be displayed when task is deleted.
     * @throws SerenityException If task index is not specified.
     */
    public String deleteTask(String input) throws SerenityException {

        String[] parts = input.split(" ");
        if (parts.length == 1) {
            throw new SerenityException("Error: Missing task index.");
        }

        int index = Integer.parseInt(input.substring(7)) - 1;
        Task t = tasks.get(index);
        tasks.remove(index);

        String numOfTasks = tasks.size() == 1 ? "task" : "tasks";
        return ("Noted. I've removed this task:\n" + t
                + "\nNow you have " + tasks.size() + " " + numOfTasks + " in the list.");
    }

    /**
     * Changes status of task.
     *
     * @param input Command to containing index of task to change status.
     * @return Message to be displayed to user when task status is changed.
     * @throws SerenityException If task index is not specified.
     */
    public String changeStatus(String input) throws SerenityException {

        String[] parts = input.split(" ");
        if (parts.length == 1) {
            throw new SerenityException("Error: Missing task index.");
        }

        String message;

        if (input.startsWith("mark")) {
            int index = Integer.parseInt(input.substring(5)) - 1;
            Task t = tasks.get(index);
            t.markAsDone();
            message = "Nice! I've marked this task as done:\n" + t;
        } else if (input.startsWith("unmark")) {
            int index = Integer.parseInt(input.substring(7)) - 1;
            Task t = tasks.get(index);
            t.markAsNotDone();
            message = "OK, I've marked this task as not done yet:\n" + t;
        } else {
            throw new SerenityException("Error: Type of task is not specified.");
        }
        return message;
    }

    /**
     * Returns string representation of TaskList.
     *
     * @return String representation.
     */
    public String findTask(String input) throws SerenityException {

        String[] parts = input.split(" ");
        if (parts.length == 1) {
            throw new SerenityException("Error: Missing keyword.");
        }

        String keyWord = parts[1].strip();
        ArrayList<Task> foundTasks = new ArrayList<>();

        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).toString().contains(keyWord)) {
                foundTasks.add(tasks.get(i));
            }
        }

        String message = "Here are the matching tasks in your list:";
        for (int j = 0; j < foundTasks.size(); j++) {
            int index = j + 1;
            message += "\n" + index + ". " + foundTasks.get(j);
        }
        return message;
    }
    
    @Override
    public String toString() {
        String message = "Here are the tasks in your list:";
        for (int i = 0; i < tasks.size(); i++) {
            int index = i + 1;
            message += "\n" + index + ". " + tasks.get(i);
        }
        return message;
    }

    /**
     * Returns string representation of TaskList to save as data.
     *
     * @return String representation to save as data.
     */
    public String toDataFormat() {
        String data = "";
        for (int i = 0; i < tasks.size(); i++) {
            data += tasks.get(i).formatData() + "\n" ;
        }
        return data;

    }
}
