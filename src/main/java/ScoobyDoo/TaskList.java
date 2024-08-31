package ScoobyDoo;
import java.util.ArrayList;
import java.util.stream.Collectors;

import exception.InputFormatException;
import task.Task;

/**
 * The TaskList class manages a list of tasks.
 * It provides functionality to add, mark, unmark, delete, and list tasks.
 */
public class TaskList {
    private final ArrayList<Task> list;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        list = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with a given list of tasks.
     *
     * @param list The ArrayList of tasks to initialize the TaskList with.
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Adds a task to the list if the list size is not exceeded.
     *
     * @param task The task to be added.
     * @return A string confirming the addition of the task or an error message if the list is full.
     */
    public String addTask(Task task) {
        if (checkListSize()) {
            list.add(task);
            return String.format(
                    "Got it. I've added this task:\n   %s\nNow you have %d %s in the list.",
                    task.toString(),
                    list.size(),
                    list.size() == 1? "task": "tasks");
        } else return "Too many tasks !!!";
    }

    private boolean checkListSize() {
        return !(list.size() > 100);
    }

    /**
     * Marks a task in the taskList as done
     *
     * @param i The index of the task to be marked as done.
     * @return A string confirming the task has been marked as done.
     * @throws InputFormatException If the provided index is out of range.
     */
    public String markTask(int i) throws InputFormatException {
        if (i > list.size() || i <= 0) {
            throw new InputFormatException("Your number is out of range");
        }
        list.get(i-1).markAsDone();
        return String.format("Nice! I've marked this task as done:\n %s", list.get(i-1).toString());
    }

    /**
     * Marks a task in the taskList as undone.
     *
     * @param i The index of the task to be marked as undone.
     * @return A string confirming the task has been marked as undone.
     * @throws InputFormatException If the provided index is out of range.
     */
    public String unMarkTask(int i) throws InputFormatException {
        if (i > list.size() || i <= 0) {
            throw new InputFormatException("Your number is out of range");
        }
        list.get(i-1).markAsUndone();
        return String.format("OK, I've marked this task as not done yet:\n %s", list.get(i-1).toString());
    }

    /**
     * Returns a string representation of all tasks in the list.
     *
     * @return A formatted string containing all tasks in the list.
     */
    public String printList() {
        String listString = "";
        int i = 1;
        for (Task task : list) {
            listString = listString.concat(i + "." + task.toString());
            ++i;
        }
        return listString;
    }

    /**
     * Deletes a task from the list.
     *
     * @param i The index of the task to be deleted.
     * @return A string confirming the deletion of the task.
     */
    public String deleteTask (int i) {
        Task deletedTask = list.remove(i - 1);
        return String.format("Noted. I've removed this task:\n %s\nNow you have %d tasks in the list."
                , deletedTask.toString(), list.size());
    }

    /**
     * Extracts the task number to be deleted from the input string.
     *
     * @param input The input string containing the delete command and task number.
     * @return The task number to be deleted.
     * @throws InputFormatException If the input format is invalid or the task number cannot be parsed.
     */
    public static int getDeleteNumber(String input) throws InputFormatException{
        String[] splitDelete = input.split(" ", 2);
        if (splitDelete.length != 2) {
            throw new InputFormatException("Oops! Please specify a number after \"delete\"");
        }
        try {
            return Integer.parseInt(splitDelete[1]);
        } catch (NumberFormatException e){
            throw new InputFormatException("Please input a number after \"delete\"");
        }
    }

    /**
     * Converts the task list to a string format suitable for file storage.
     *
     * @return A string representation of all tasks in the list, formatted for file storage.
     */
    public String toFileFormatString() {
        //using a combiner to add all the tasks string
        //only append the file once, which is when bye is called
        return list.stream().reduce("", (a, b) -> b.toFileFormatString() + "\n" + a, (a,b) -> a  + b);
    }

    /**
     * Finds and returns a new TaskList containing all tasks that match the specified target word.
     * The match is based on the task descriptions containing the target word.
     *
     * @param targetWord The word to search for within the task descriptions.
     * @return A new TaskList containing all tasks that match the specified target word.
     */
    public TaskList find(String targetWord) {
        ArrayList<Task> matchedTask = this.list.stream().filter(task -> task.find(targetWord))
                .collect(Collectors.toCollection(ArrayList<Task>::new));
        return new TaskList(matchedTask);
    }

}
