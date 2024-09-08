package duke.tasks;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import duke.exceptions.InvalidDeadlineException;
import duke.exceptions.InvalidEventException;
import duke.exceptions.InvalidTaskException;
import duke.exceptions.InvalidTodoDescriptionException;
import duke.exceptions.UnknownMessageException;
import duke.ui.Ui;

/**
 * The {@code TaskList} class is responsible for managing a collection of tasks.
 * It provides methods to add, retrieve, delete, and manage different types of tasks,
 * including ToDos, Deadlines, and Events.
 * <p>
 * This class maintains an internal list of {@link Task} objects and supports operations
 * like finding tasks that occur at a specific time, processing user inputs to add tasks,
 * and handling task-related exceptions such as invalid descriptions or dates.
 * </p>
 * <p>
 * The {@code TaskList} interacts with the user via the {@link Ui} class for formatted task output
 * </p>
 */
public class TaskList {

    private List<Task> tasks;

    /**
     * Constructs a new {@code TaskList} with an empty list of tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Retrieves the list of tasks.
     *
     * @return a list of {@code Task} objects managed by this {@code TaskList}.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Retrieves a specific task from the list by its index.
     *
     * @param index the index of the task to retrieve.
     * @return the {@code Task} object at the specified index.
     * @throws IndexOutOfBoundsException if the index is out of bounds.
     */
    public Task getTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            return tasks.get(index);
        }
        throw new IndexOutOfBoundsException("Invalid task index.");
    }

    /**
     * Retrieves the last task from the list.
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
     * Retrieves a list of tasks that occur on the specified date and time.
     *
     * @param dateTime the {@code LocalDateTime} to filter tasks by.
     * @return a list of tasks that occur at the given {@code LocalDateTime}.
     */
    public List<Task> getTasksOccurring(LocalDateTime dateTime) {
        return tasks.stream()
                .filter(task -> task.occurring(dateTime))
                .collect(Collectors.toList());
    }

    /**
     * Sets the tasks managed by this {@code TaskList}.
     *
     * @param tasks a list of {@code Task} objects to set.
     */
    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Deletes a task from the task list based on the provided index.
     *
     * @param index the index of the task to delete.
     * @return the {@code Task} object that was removed.
     * @throws IndexOutOfBoundsException if the index is out of bounds.
     */
    public Task deleteTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            return tasks.remove(index);
        }
        throw new IndexOutOfBoundsException("Invalid task index.");
    }

    /**
     * Adds new tasks to the task list based on the user's inputs. The inputs are parsed to
     * determine the type of task (e.g., ToDo, Deadline, or Event).
     *
     * @param userInputs the raw input strings from the user.
     * @return a string summarizing the added tasks.
     * @throws InvalidTaskException           if the task type is unknown or unrecognized.
     */
    public String addTask(String... userInputs) throws InvalidTaskException {
        StringBuilder result = new StringBuilder();

        for (String userInput : userInputs) {
            try {
                result.append(processUserInput(userInput)).append("\n");
            } catch (UnknownMessageException | InvalidTodoDescriptionException
                     | InvalidDeadlineException | InvalidEventException e) {
                throw new InvalidTaskException(e.getMessage()); // Rethrow as InvalidTaskException
            }
        }

        return result.toString().trim();
    }

    /**
     * Processes a single user input to determine the task type and add it to the task list.
     *
     * @param userInput the raw input string from the user.
     * @return a string summarizing the added task.
     * @throws UnknownMessageException         if the task type is unknown or unrecognized.
     * @throws InvalidTodoDescriptionException if the task is a ToDo but lacks a description.
     */
    private String processUserInput(String userInput) throws UnknownMessageException, InvalidTodoDescriptionException,
            InvalidDeadlineException, InvalidEventException {
        TaskEnum taskType = determineTaskType(userInput);
        StringBuilder result = new StringBuilder();

        switch (taskType) {
        case TODOS:
            processTodoTask(userInput);
            break;
        case DEADLINE:
            processDeadlineTask(userInput);
            break;
        case EVENT:
            processEventTask(userInput);
            break;
        default:
            throw new UnknownMessageException("Unknown task type.");
        }

        result.append(Ui.formatAddTask(this.getTasks().size(), this.getLastTask()));
        return result.toString();
    }

    /**
     * Processes a ToDo task and adds it to the task list.
     *
     * @param userInput the raw input string from the user.
     * @throws InvalidTodoDescriptionException if the task is a ToDo but lacks a description.
     */
    private void processTodoTask(String userInput) throws InvalidTodoDescriptionException {
        try {
            String todoDescription = userInput.split(" ", 2)[1];
            tasks.add(new ToDos(todoDescription));
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidTodoDescriptionException("Please include a description of your todo task!");
        }
    }

    /**
     * Processes a Deadline task and adds it to the task list.
     *
     * @param userInput the raw input string from the user.
     */
    private void processDeadlineTask(String userInput) throws InvalidDeadlineException {
        String[] deadlineInfo = userInput.split("/by");
        String deadlineDescription = deadlineInfo[0].replace("deadline", "").trim();
        String deadlineDate = deadlineInfo[1].trim();

        try {
            tasks.add(new Deadline(deadlineDescription, deadlineDate));
        } catch (InvalidDeadlineException e) {
            throw new InvalidDeadlineException("Please enter a valid deadline!");
        }
    }

    /**
     * Processes an Event task and adds it to the task list.
     *
     * @param userInput the raw input string from the user.
     */
    private void processEventTask(String userInput) throws InvalidEventException {
        String[] eventInfo = userInput.split("/from");
        String eventDescription = eventInfo[0].replace("event", "").trim();
        String[] eventTime = eventInfo[1].split("/to");
        String start = eventTime[0].trim();
        String end = eventTime[1].trim();

        try {
            tasks.add(new Event(eventDescription, start, end));
        } catch (InvalidEventException e) {
            throw new InvalidEventException("Please enter a valid event!");
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
