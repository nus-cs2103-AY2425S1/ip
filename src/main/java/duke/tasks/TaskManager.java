package duke.tasks;

import duke.exceptions.EmptyTodoDescriptionException;
import duke.exceptions.UnknownMessageException;

import java.util.ArrayList;

/**
 * The {@code TaskManager} class is responsible for managing a collection of tasks.
 * It supports adding, retrieving, deleting, and identifying task types. Tasks
 * include ToDos, Deadlines, and Events.
 * <p>
 * The class manages an internal list of {@link Task} objects and provides methods
 * for interacting with and manipulating this list. It also handles errors related
 * to invalid task inputs or operations.
 * </p>
 */
public class TaskManager {

    /**
     * The list of tasks being managed by the {@code TaskManager}.
     */
    private final ArrayList<Task> tasks;

    /**
     * Constructs a new {@code TaskManager} with an empty task list.
     */
    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Retrieves the list of tasks managed by this {@code TaskManager}.
     *
     * @return an {@code ArrayList} of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Retrieves the task at the specified index in the task list.
     *
     * @param index the index of the task to retrieve.
     * @return the {@code Task} at the specified index.
     * @throws IndexOutOfBoundsException if the index is out of the range of the task list.
     */
    public Task getTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            return tasks.get(index);
        }
        throw new IndexOutOfBoundsException("Invalid task index.");
    }

    /**
     * Retrieves the last task in the task list.
     *
     * @return the last {@code Task} in the task list.
     * @throws IllegalStateException if the task list is empty.
     */
    public Task getLastTask() {
        if (tasks.isEmpty()) {
            throw new IllegalStateException("No tasks available.");
        }
        return tasks.get(tasks.size() - 1);
    }

    /**
     * Deletes the task at the specified index from the task list.
     *
     * @param index the index of the task to delete.
     * @return the {@code Task} that was removed.
     * @throws IndexOutOfBoundsException if the index is out of the range of the task list.
     */
    public Task deleteTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            return tasks.remove(index);
        }
        throw new IndexOutOfBoundsException("Invalid task index.");
    }

    /**
     * Adds a new task to the task list based on the user's input.
     * The input is parsed to determine the type of task (ToDo, Deadline, or Event).
     *
     * @param userInput the raw input string from the user.
     * @throws UnknownMessageException if the task type is unknown or unrecognized.
     * @throws EmptyTodoDescriptionException if the task is a ToDo but lacks a description.
     */
    public void addTask(String userInput) throws UnknownMessageException, EmptyTodoDescriptionException {
        TaskEnum taskType = determineTaskType(userInput);

        switch (taskType) {
            case TODOS:
                try {
                    String todoDescription = userInput.split(" ", 2)[1];
                    tasks.add(new ToDos(todoDescription));
                    break;
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new EmptyTodoDescriptionException("Please include a description of your todo task!");
                }
            case DEADLINE:
                String[] deadlineInfo = userInput.split("/by");
                String deadlineDescription = deadlineInfo[0].replace("deadline", "").trim();
                String deadlineDate = deadlineInfo[1].trim();
                tasks.add(new Deadline(deadlineDescription, deadlineDate));
                break;
            case EVENT:
                String[] eventInfo = userInput.split("/from");
                String eventDescription = eventInfo[0].replace("event", "").trim();
                String[] eventTime = eventInfo[1].split("/to");
                String start = eventTime[0].trim();
                String end = eventTime[1].trim();
                tasks.add(new Event(eventDescription, start, end));
                break;
            default:
                throw new UnknownMessageException("Unknown task type.");
        }
    }

    /**
     * Determines the type of task from the user's input.
     *
     * @param input the raw input string from the user.
     * @return the {@code TaskEnum} representing the type of task.
     * @throws UnknownMessageException if the task type is unknown or unrecognized.
     */
    private TaskEnum determineTaskType(String input) throws UnknownMessageException {
        if (input.startsWith("todo")) {
            return TaskEnum.TODOS;
        } else if (input.startsWith("deadline")) {
            return TaskEnum.DEADLINE;
        } else if (input.startsWith("event")) {
            return TaskEnum.EVENT;
        } else {
            throw new UnknownMessageException();
        }
    }
}
