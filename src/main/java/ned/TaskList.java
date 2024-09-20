package ned;

import java.util.ArrayList;

import ned.exceptions.DuplicateTaskException;
import ned.exceptions.InvalidIndexException;
import ned.exceptions.NedException;
import ned.tasks.Task;

/**
 * The {@code TaskList} class manages a collection of tasks within the application.
 * It provides methods to add, remove, and manipulate tasks, as well as to search and list them.
 * This class interacts closely with the {@code Ui} class to display messages and updates to the user,
 * and it handles exceptions through the {@code NedException} class.
 *
 * <p>Key functionalities include:
 * <ul>
 *   <li>Adding a task to the list via {@link #addTask(Task, Ui)}.</li>
 *   <li>Removing a task from the list via {@link #removeTask(int, Ui)}.</li>
 *   <li>Marking tasks as done or undone using {@link #markTaskAsDone(int, Ui)} and
 *   {@link #markTaskAsUndone(int, Ui)}.</li>
 *   <li>Listing all tasks through {@link #listTasks(Ui)}.</li>
 *   <li>Finding tasks that match a specific search term using {@link #findRelatedTasks(String)}.</li>
 *   <li>Retrieving the size of the task list with {@link #getSize()}.</li>
 *   <li>Getting a task's text representation via {@link #getTaskTextForm(int)}.</li>
 * </ul>
 *
 * <p>The class ensures proper error handling by throwing {@code NedException} when invalid indices are provided.
 * It maintains an internal {@code ArrayList} of {@code Task} objects, representing the user's tasks.
 *
 * @see Task
 * @see Ui
 * @see NedException
 */
public class TaskList {
    private ArrayList<Task> listOfTasks;

    /**
     * Constructs a TaskList instance with an instance of an Arraylist
     *
     * @param listOfTasks ArrayList parameterized with Task class
     */
    public TaskList(ArrayList<Task> listOfTasks) {
        this.listOfTasks = listOfTasks;
    }

    /**
     * Removes a specified task from the list of tasks.
     *
     * @param index - Index of the task to be removed
     * @param uiInstance - Instance of the UI class to handle the printing
     * @throws NedException - Thrown if the index given is not of an item in the list of tasks
     */
    public void removeTask(int index, Ui uiInstance) throws NedException {
        try {
            Task selectedTask = getTask(index);
            this.listOfTasks.remove(index);
            uiInstance.removeTasksToNedDialogue(selectedTask, this.listOfTasks.size());
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIndexException("Sorry m'lord, your command must specify an index within the bounds "
                    + "of the list size");
        }
    }

    /**
     * Returns a specified task from the list of tasks.
     * @param index - Index of the task to be selected
     * @return Task - The task whose index corresponds to the passed index
     * @throws NedException - Thrown if the index given is not of an item in the list of tasks
     */
    private Task getTask(int index) throws NedException {
        try {
            return this.listOfTasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIndexException("Sorry m'lord, your command must specify an index within the "
                    + "bounds of the list size");
        }
    }

    /**
     * Adds a given task to the current list of tasks and requests for it to be printed out.
     *
     * @param newTask - The task which is to be added
     * @param uiInstance - The Ui instance which is used to print out the addition's success to the user
     */
    public void addTask(Task newTask, Ui uiInstance) throws NedException {
        assert this.listOfTasks != null : "List of tasks cannot be null!";
        if (isTaskADuplicate(newTask)) {
            throw new DuplicateTaskException(String.format("Sorry m'lord, it seems that this task, %s, already "
                    + "appears in the task list.\n", newTask) + "Use the list command to confirm this error m'lord.");
        }
        this.listOfTasks.add(newTask);
        uiInstance.addTasksToNedDialogue(newTask, this.listOfTasks.size());
    }

    private boolean isTaskADuplicate(Task newTask) {
        return this.listOfTasks.contains(newTask);
    }

    /**
     * Causes all tasks in the current list of tasks to be printed out.
     *
     * @param uiInstance - The Ui instance used to print out the list of tasks
     */
    public void listTasks(Ui uiInstance) {
        assert this.listOfTasks != null : "List of tasks cannot be null!";
        int sizeOfListOfTasks = listOfTasks.size();
        if (sizeOfListOfTasks == 0) {
            uiInstance.addToNedDialogue("M'lord, there are currently no tasks in your list.");
        } else {
            for (int i = 0; i < listOfTasks.size(); i++) {
                String task = Ui.INDENTATIONS + String.format("%d.%s \n", i + 1, listOfTasks.get(i));
                uiInstance.addToNedDialogue(task);
            }
        }
    }

    /**
     * Used to mark a given task as done.
     *
     * @param index - The index of the task in the list of tasks
     * @param uiInstance - The Ui instance used to print out the change in the status of the task
     * @throws NedException - Thrown if the index given is not in the list of tasks.
     */
    public void markTaskAsDone(int index, Ui uiInstance) throws NedException {
        Task selectedTask = getTask(index);
        assert selectedTask != null : "Null should not have been added to the list!";
        selectedTask.markAsDone();
        uiInstance.addToNedDialogue("Good work. One down, more to go!");
        uiInstance.addToNedDialogue(selectedTask.toString());
    }

    /**
     * Used to mark a given task as not done.
     *
     * @param index - The index of the task in the list of tasks
     * @param uiInstance - The Ui instance used to print out the change in the status of the task
     * @throws NedException - Thrown if the index given is not in the list of tasks.
     */
    public void markTaskAsUndone(int index, Ui uiInstance) throws NedException {
        Task selectedTask = getTask(index);
        assert selectedTask != null : "Null should not have been added to the list!";
        selectedTask.markAsNotDone();
        uiInstance.addToNedDialogue("Oh no. One back up, more to go!");
        uiInstance.addToNedDialogue(selectedTask.toString());
    }

    /**
     * Gets the size of the current list of tasks.
     *
     * @return - The size of the current list of tasks
     */
    public int getSize() {
        assert this.listOfTasks != null : "List of tasks cannot be null!";
        return this.listOfTasks.size();
    }

    /**
     * Gets the string representation of a task specified by its index.
     *
     * @param index - Index of the task to be selected
     * @return - The string representation of the task
     * @throws NedException - Thrown if the index provided does not belong to any task on the list of tasks
     */
    public String getTaskTextForm(int index) throws NedException {
        Task selectedTask = getTask(index);
        assert selectedTask != null : "Null should not have been added to the list!";
        return selectedTask.toTextForm();
    }

    /**
     * Gets a filtered task list of tasks which contain the search term as a substring.
     *
     * @param searchTerm - The search term used to filter the list of tasks
     * @return - Filtered task list
     */
    public TaskList findRelatedTasks(String searchTerm) {
        assert searchTerm != null : "search term cannot be null!";
        assert !(searchTerm.isEmpty()) : "search term cannot be empty!";
        String regexSearchPattern = ".*" + searchTerm + ".*";
        int sizeOfArray = this.listOfTasks.size();
        int index = 1;
        ArrayList<Task> listOfRelatedTasks = new ArrayList<>();
        for (int i = 0; i < sizeOfArray; i++) {
            if (this.listOfTasks.get(i).isMatchingPattern(regexSearchPattern)) {
                listOfRelatedTasks.add(this.listOfTasks.get(i));
            }
        }
        return new TaskList(listOfRelatedTasks);
    }
}
