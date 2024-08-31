package Joseph.Tasks;

import Joseph.*;
import Joseph.Exceptions.InsufficientDetailsException;

import java.util.ArrayList;

/**
 * Contains the task list as well as operations for adding. deleting, marking and unmarking tasks.
 * Handles parsed commands.
 */
public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Adds a task to the task list.
     * @param task The task to be added
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the task list.
     * @param index The index of the task to be deleted. User input is 1-indexed.
     * @return The deleted task.
     * @throws IndexOutOfBoundsException If no task exists at the specified index.
     */
    public Task deleteTask(int index) throws IndexOutOfBoundsException {
        return tasks.remove(index - 1);
    }

    /**
     * Retrieves a task from the task list.
     * @param index The index of the task to be retrieved. User input is 1-indexed.
     * @return The task at the specified index.
     * @throws IndexOutOfBoundsException If no task exists at the specified index.
     */
    public Task getTask(int index) throws IndexOutOfBoundsException {
        return tasks.get(index - 1);
    }

    /**
     * Marks a task as done.
     * @param index The index of the task to be marked. User input is 1-indexed.
     * @throws IndexOutOfBoundsException If no task exists at the specified index.
     */
    public void markTask(int index) throws IndexOutOfBoundsException {
        tasks.get(index - 1).setDone();
    }
    /**
     *
     * Unmarks a task as not done.
     * @param index The index of the task to be unmarked. User input is 1-indexed.
     * @throws IndexOutOfBoundsException If no task exists at the specified index.
     */
    public void unmarkTask(int index) throws IndexOutOfBoundsException {
        tasks.get(index - 1).unDone();
    }

    public int size() {
        return tasks.size();
    }

    /**
     * Handles the commands to add, delete, mark or unmark tasks.
     * @param command The command to handle.
     * @param input The user input.
     * @param parser The parser to interpret the input.
     * @param ui The UI to interact with the user.
     * @param storage The storage to save the task list.
     */
    public void handleCommand(Parser.Command command, String input,
                              Parser parser, UI ui, Storage storage) {
        try {
            switch (command) {
                case LIST:
                    ui.printListMessage(tasks);
                    break;
                case MARK:
                    int markNum = parser.parseTaskNumber(input, command.getCommandText());
                    String markDetails = getTask(markNum).getDetails();
                    markTask(markNum);
                    storage.saveTasks(tasks);
                    ui.printMarkMessage(markDetails);
                    break;
                case UNMARK:
                    int unmarkNum = parser.parseTaskNumber(input, command.getCommandText());
                    String unmarkDetails = getTask(unmarkNum).getDetails();
                    unmarkTask(unmarkNum);
                    storage.saveTasks(tasks);
                    ui.printUnmarkMessage(unmarkDetails);
                    break;
                case TODO:
                    String todoDetails = parser.parseTodoDetails(input, command.getCommandText());
                    ToDo todo = new ToDo(todoDetails);
                    addTask(todo);
                    storage.saveTasks(tasks);
                    ui.printTodoMessage(todo.getDetails());
                    break;
                case DEADLINE:
                    String[] deadlineDetails = parser.parseDeadlineDetails(input, command.getCommandText());
                    Deadline deadline = new Deadline(deadlineDetails[0], deadlineDetails[1]);
                    addTask(deadline);
                    storage.saveTasks(tasks);
                    ui.printDeadlineMessage(deadline.getDetails());
                    break;
                case EVENT:
                    String[] eventDetails = parser.parseEventDetails(input, command.getCommandText());
                    JEvent event = new JEvent(eventDetails[0], eventDetails[1], eventDetails[2]);
                    addTask(event);
                    storage.saveTasks(tasks);
                    ui.printJEventMessage(event.getDetails());
                    break;
                case DELETE:
                    int deleteNum = parser.parseTaskNumber(input, command.getCommandText());
                    String deleteDetails = getTask(deleteNum).getDetails();
                    deleteTask(deleteNum);
                    storage.saveTasks(tasks);
                    ui.printDeleteMessage(deleteDetails);
                    break;
                default:
                    ui.printUnrecognisedMessage();
                    break;
            }
        } catch (InsufficientDetailsException e) {
            ui.printErrorMessage(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("A task does not exist at that index!");
        }
    }
}
