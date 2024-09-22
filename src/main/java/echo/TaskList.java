package echo;
import java.io.FileNotFoundException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * The TaskList class manages a list of tasks, including adding, deleting, and marking tasks as done or undone.
 */
public class TaskList {
    List<Task> tasks;

    /**
     * Constructs a TaskList object with an existing list of tasks. from Storage.load()
     *
     * @param tasks The list of tasks to be managed.
     * @throws EchoException       If there is an error related to task management.
     * @throws FileNotFoundException If the file containing tasks is not found.
     */
    public TaskList(List<Task> tasks) throws EchoException, FileNotFoundException {
        this.tasks = tasks;

    }

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Converts the list of tasks to a string in a format suitable for saving to a file.
     *
     * @param tasks The list of tasks to convert to a string.
     * @return A string representation of the list of tasks.
     */
    public static String listToString(List<Task> tasks) {
        StringBuilder listString = new StringBuilder();

        for (Task task : tasks) {
            listString.append(task.getTypeLetter()).append("|").append(task.getIsDone()).append("|").append(task.getTaskDes()).append("|").append(task.getAdd()).append("\n");

        }
        return listString.toString();
    }


    public String find(String toFind) {
        List<Task> taskList = new ArrayList<>();
        for (Task task : tasks) {

            String des = task.getTaskDes();
            if (Parser.isPresent(des, toFind)) {
                taskList.add(task);
            }
        }
        return Ui.showClassFound(taskList);
    }




    /**
     * Converts the current list of tasks to a string in a format suitable for saving to a file.
     *
     * @return A string representation of the current list of tasks.
     */

    public  String listToString() {
        StringBuilder listString = new StringBuilder();
        for (Task task : tasks) {
            listString.append(task.getTypeLetter()).append("|").append(task.getIsDone()).append("|").append(task.getTaskDes()).append("|").append(task.getAdd()).append("\n");

        }
        return listString.toString();
    }


    /**
     * Deletes a task from the list based on the task number.
     *
     * @param num The task number to be deleted.
     * @return The task that was deleted.
     * @throws EchoException If the task number is invalid.
     */
    public Task deleteTask( int num) throws EchoException {
        try {

            if (num > 0 && num <= this.tasks.size()) {
                return this.tasks.remove(num - 1);
            } else {
                throw new EchoException("Invalid task number.");
            }
        } catch (NumberFormatException e) {
            throw new EchoException("Please enter a valid number.");
        }
    }

    /**
     * Marks a task as done based on the task number.
     *
     * @param index The task number to be marked as done.
     * @throws EchoException If the task number is invalid.
     */
    public void markTask(int index) throws EchoException {
        if (index > 0 && index <= tasks.size()) {
            Task doneTask = tasks.get(index - 1);
            doneTask.setDone();
        } else {
            throw new EchoException("Invalid task number.");
        }
    }

    /**
     * Unmarks a task as undone based on the task number.
     *
     * @param index The task number to be unmarked as undone.
     * @throws EchoException If the task number is invalid.
     */
    public void unmarkTask(int index) throws EchoException {
        if (index > 0 && index <= tasks.size()) {
            Task undoneTask = tasks.get(index - 1);
            undoneTask.setUndone();
        } else {
            throw new EchoException("Invalid task number.");
        }
    }


    /**
     * Retrieves a task from the list based on the task number.
     *
     * @param index The task number to retrieve.
     * @return The task corresponding to the specified task number.
     */
    public Task getTask(int index) {
        return tasks.get(index - 1);
    }

    /**
     * Returns the list of tasks managed by this TaskList.
     *
     * @return The list of tasks.
     */
    public List<Task> getTasks() {
        return tasks;
    }


    /**
     * Adds a Deadline task to the list based on the input string.
     *
     * @param input The input string containing the task description and deadline.
     * @return The Deadline task that was added.
     * @throws EchoException         If the input format is incorrect.
     * @throws DateTimeParseException If the deadline format is invalid.
     */
    public Deadline addDeadline( String input) throws EchoException, DateTimeParseException {
        try {
            String[] details = input.split(" /by ", 2);
            if (details.length == 2) {
                Deadline deadlineTask = new Deadline(details[0], details[1]);
                this.tasks.add(deadlineTask);
                return deadlineTask;
            } else {
                throw new EchoException("Please specify the task description and deadline.");

            }
        } catch (DateTimeParseException e) {
            throw new EchoException("Enter date in YYYY-MM-DD format");
        }
    }


    /**
     * Adds an Events task to the list based on the input string.
     *
     * @param input The input string containing the task description and event time.
     * @return The Events task that was added.
     * @throws EchoException         If the input format is incorrect.
     * @throws DateTimeParseException If the event time format is invalid.
     */
    public Events addEvent( String input) throws EchoException, DateTimeParseException {
        try {
            String eventDes = Parser.parseEventDes(input);
            String[] times = Parser.parseEventTime(input);
            Events eventTask = new Events(eventDes, times[0], times[1]);
            this.tasks.add(eventTask);
            return eventTask;
        }  catch (DateTimeParseException e) {
            throw new EchoException("Enter date in YYYY-MM-DD format");
        }

    }

    /**
     * Adds a Todo task to the list based on the task description.
     *
     * @param taskDescription The description of the task.
     * @return The Todo task that was added.
     */
    public Todo addTodo(String taskDescription) {
        Todo todo = new Todo(taskDescription);
        this.tasks.add(todo);
        return todo;
    }


    /**
     * Returns the number of tasks in the list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return tasks.size();
    }
}
