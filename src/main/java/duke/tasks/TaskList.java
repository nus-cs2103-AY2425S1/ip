/**
 * This class represents a list containing tasks.
 * It provides methods to add, mark, unmark and print tasks in the list.
 */
package duke.tasks;
import duke.ui.Ui;
import duke.data.TaskDataBase;
import duke.exceptions.TaskNotFoundException;
import duke.exceptions.InvalidDateException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import duke.tasks.Task;
public class TaskList {
    private static TaskList tasks;
    private List<Task> listOfTasks;
    private Ui ui;

    protected TaskList(Ui ui) {
        try {
            listOfTasks = TaskDataBase.load();
            this.ui = ui;
        } catch (IOException e) {
            ui.printMessage("Oops! There is a issue with file database.");
        } catch (InvalidDateException e) {
            ui.printMessage(e.toString());
        }
    }

    /**
     * Initializes the `TaskList` if it hasn't been initialized yet.
     *
     * @param ui The `Ui` object used to interact with the user during task initialization.
     * @return The initialized `TaskList` instance.
     */
    public static TaskList init(Ui ui) {
        if (tasks == null) {
            tasks = new TaskList(ui);
        }
        return tasks;
    }

    /**
     * Adds new task to todolist.
     *
     * @param task The name of task to be added.
     */
    public void addTask(Task task) {
        listOfTasks.add(task);
        TaskDataBase.save(listOfTasks, ui);
        ui.printMessage("Got it. I've added this task:" + "\n"
                + "  " + task.toString() + "\n"
                + "Now you have "  + listOfTasks.size() + " tasks in the list.");
    }

    /**
     * Marks specific task as done.
     *
     * @param index The task number to be marked.
     */
    public void markTask(int index) throws TaskNotFoundException {
        if (index > 0 && index <= listOfTasks.size()) {
            Task task = listOfTasks.get(index - 1);
            task.markAsDone();
            TaskDataBase.save(listOfTasks, ui);
            ui.printMessage("Nice! I've marked this task as done:\n" + "  " + task.toString());
        } else {
            throw new TaskNotFoundException();
        }
    }

    /**
     * Marks specific task as not done.
     *
     * @param index The task number to be marked.
     */
    public void unmarkTask(int index) throws TaskNotFoundException {
        if (index > 0 && index <= listOfTasks.size()) {
            Task task = listOfTasks.get(index - 1);
            TaskDataBase.save(listOfTasks, ui);
            task.markAsNotDone();
            ui.printMessage("OK, I've marked this task as not done yet:\n" + "  " + task.toString());
        } else {
            throw new TaskNotFoundException();
        }
    }

    /**
     * Deletes a task from the task list at the specified index.
     *
     * @param index The index of the task to be deleted (1-based index).
     * @throws TaskNotFoundException If the index is out of bounds, meaning no task exists at the specified index.
     */
    public void deleteTask(int index) throws TaskNotFoundException {
        if (index > 0 && index <= listOfTasks.size()) {
            Task removedTask = listOfTasks.remove(index - 1);
            TaskDataBase.save(listOfTasks, ui);
            ui.printMessage("Noted. I've removed this task:" + "\n" +
                    "  " + removedTask + "\n" + "Now you have "
                    + listOfTasks.size() + " tasks in the list." );
        } else {
            throw new TaskNotFoundException();
        }
    }

    /**
     * Print all tasks added to the list.
     *
     * @return The description of all tasks added.
     */
    public String printList(){
        StringBuilder tasks = new StringBuilder("Here are the tasks in your list:" + "\n");

        for (int i = 0; i < listOfTasks.size(); i++) {
            tasks.append(i + 1).append(".").append(listOfTasks.get(i).toString());
            if (i < listOfTasks.size() - 1) {
                tasks.append("\n");
            }
        }
        return tasks.toString();
    }

    /**
     * Prints all tasks matching user input.
     */
    public void findTask(String keyword) throws TaskNotFoundException {
        List<Task> matchingTasks = new ArrayList<>();
        for (int i = 0; i < listOfTasks.size(); i++) {
            Task task = listOfTasks.get(i);
            if (task.getName().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        if (matchingTasks.isEmpty()) {
            throw new TaskNotFoundException();
        }

        StringBuilder result = new StringBuilder();
        result.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < matchingTasks.size(); i++) {
            result.append((i + 1) + ". " + matchingTasks.get(i) + "\n");
        }
        ui.printMessage(result.toString());
    }
}
