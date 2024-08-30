package twilight;

import java.util.ArrayList;

/**
 * Stores the tasks given to the chatbot and allows modification.
 */
public class TaskList {
    protected ArrayList<Task> tasks;

    /**
     * Instantiates a tasklist object based on an exisiting an arraylist</task>.
     *
     * @param tasks An arraylist of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Instantiates an empty tasklist with an empty arraylist.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Prints the tasks stored in the list.
     */
    public void list() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("" + (i + 1) + ". " + tasks.get(i).toString());
        }
    }

    /**
     * Marks task as complete and returns a message to show the change.
     *
     * @param taskNum The number of the tasks to be modified.
     * @return A string to user to indicate the change.
     */
    public String mark(int taskNum) {
        tasks.get(taskNum).setDone();
        return tasks.get(taskNum).toString();
    }

    /**
     * Unmarks the task from complete to incomplete and returns a message to show the change.
     *
     * @param taskNum The number of the task to be unmarked.
     * @return A string which shows the task has been marked incomplete.
     */
    public String unmark(int taskNum) {
        tasks.get(taskNum).SetUndone();
        return tasks.get(taskNum).toString();
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Adds a particular event of any type to the list of events.
     *
     * @param t The particular task to be added.
     * @return A string to show the event has been added and indicate the number of events in the tasklist.
     */
    public String add(Task t) {
        tasks.add(t);
        return "added: " + tasks.get(tasks.size() - 1).toString() + "\n" + "There are " + tasks.size() + " tasks in the list";
    }

    /**
     * Deletes a task from the task list.
     *
     * @param taskNum The number of the task to be deleted.
     * @return A message to inform that the task has been deleted and stating the number of remaining tasks.
     * @throws InvalidInputException If the value of the task to be deleted is greater than the number of tasks that exist.
     */
    public String delete(int taskNum) throws InvalidInputException {
        try {
            tasks.remove(taskNum);
            return "The task has been successfully removed leaving: " + tasks.size() + " tasks";
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidInputException("Task " + (taskNum + 1) + " does not exist");
        }
    }
}

