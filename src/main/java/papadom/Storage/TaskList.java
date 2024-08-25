package papadom.Storage;

import papadom.Exceptions.NoTaskException;
import papadom.Exceptions.NoTaskNumberException;
import papadom.Exceptions.WrongTaskNumberException;
import papadom.tasks.Task;

import java.util.ArrayList;

public class TaskList {
    private final Storage storage;
    private final ArrayList<Task> tasks = new ArrayList<>();
    public TaskList(Storage storage) {
        this.storage = storage;
    }
    public ArrayList<Task> getTasks () {
        return this.tasks;
    }
    public String outputList() {
        String finalList = " Here are the tasks in your list:";
        for (int i = 0; i < this.tasks.size(); i++) {
            Task task = this.tasks.get(i);
            if (task == null) {
                break;
            }
            finalList += "\n " + (i + 1) + "." + task.toString();
        }
        return finalList;
    }
    public String addToList(Task task) throws NoTaskException {
        if (task.toString() == "") {
            throw new NoTaskException();
        }
        this.tasks.add(task);
        this.storage.addTaskToDatabase(task);
        String response = " Got it. I've added this task:\n  " + task.toString() + "\n"
                + " Now you have " + (this.tasks.size()) + " tasks in the list.";
        return response;
    }
    public String deleteEvent(String text) throws NoTaskNumberException, WrongTaskNumberException {
        String[] parts = text.split(" ");
        if (parts.length <= 1) {
            throw new NoTaskNumberException();
        }
        try {
            int taskIndex = Integer.parseInt(parts[1]) - 1;
            if (taskIndex >= this.tasks.size()) {
                throw new WrongTaskNumberException();
            }
            Task taskToBeDeleted = this.tasks.get(taskIndex);
            this.tasks.remove(taskIndex);
            return " Noted. I've removed this task:\n  " + taskToBeDeleted + "\n Now you have " + this.tasks.size() + " tasks in the list.";
            // Proceed with your logic using taskIndex
        } catch (NumberFormatException e) {
            throw new NoTaskNumberException(); // Throw the custom exception if parts[1] is not an integer
        }
    }
    public String markTask(String text) throws NoTaskNumberException, WrongTaskNumberException {
        String[] parts = text.split(" ");
        if (parts.length <= 1) {
            throw new NoTaskNumberException();
        }
        try {
            int taskIndex = Integer.parseInt(parts[1]) - 1;
            if (taskIndex >= this.tasks.size()) {
                throw new WrongTaskNumberException();
            }
            Task task = this.tasks.get(taskIndex);
            task.markAsDone();
            return " Nice! I've marked this task as done:\n  " + task;
        } catch (NumberFormatException e) {
            throw new NoTaskNumberException(); // Throw the custom exception if parts[1] is not an integer
        }
    }
    public String unmarkTask(String text) throws NoTaskNumberException, WrongTaskNumberException {
        String[] parts = text.split(" ");
        if (parts.length <= 1) {
            throw new NoTaskNumberException();
        }
        try {
            int taskIndex = Integer.parseInt(parts[1]) - 1;
            if (taskIndex >= this.tasks.size()) {
                throw new WrongTaskNumberException();
            }
            Task task = this.tasks.get(taskIndex);
            task.unmark();
            return " OK, I've marked this task as not done yet:\n  " + task;
            // Proceed with your logic using taskIndex
        } catch (NumberFormatException e) {
            throw new NoTaskNumberException(); // Throw the custom exception if parts[1] is not an integer
        }
    }
}
