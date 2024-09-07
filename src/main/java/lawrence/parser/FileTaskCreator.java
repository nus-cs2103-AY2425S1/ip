package lawrence.parser;

import java.time.LocalDateTime;

import lawrence.task.Deadline;
import lawrence.task.Event;
import lawrence.task.Task;
import lawrence.task.TaskType;
import lawrence.task.Todo;
import lawrence.utils.DateParser;

/**
 * The concrete implementation of {@link TaskCreator} used to parse
 * file input into a {@link Task} object.
 */
public class FileTaskCreator implements TaskCreator {
    private static final int NUMBER_OF_DEADLINE_PARAMETERS = 3;
    private static final int NUMBER_OF_EVENT_PARAMETERS = 4;
    private static final int NUMBER_OF_TODO_PARAMETERS = 2;

    /**
     * Converts a string input from a file containing task information
     * into a {@link Task} object.
     *
     * @param input the string containing information about a task object
     * @return a {@link Task} object
     * @throws IllegalArgumentException if input is invalid
     */
    @Override
    public Task createTask(String input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException("Cannot create task from empty input!");
        }

        // separate the string containing information about the task type into index 0
        String[] inputComponents = input.split(" \\| ", 2);

        if (inputComponents.length != 2) {
            throw new IllegalArgumentException("Unable to parse input from file");
        }

        // parse the type of task that needs to be created
        TaskType type = TaskType.fromString(inputComponents[0]);

        Task t;
        switch (type) {
        case DEADLINE:
            t = createDeadlineTask(inputComponents[1]);
            break;
        case EVENT:
            t = createEventTask(inputComponents[1]);
            break;
        case TODO:
            t = createTodoTask(inputComponents[1]);
            break;
        default:
            throw new IllegalArgumentException("Unknown task type: " + type);
        }
        return t;
    }

    /**
     * Creates and returns a {@link Deadline} object based on the
     * input information provided.
     *
     * @param input a string containing information about the {@link Deadline} object
     * @return a {@link Deadline} object
     * @throws IllegalArgumentException if the input is invalid
     * @see Deadline
     */
    private Deadline createDeadlineTask(String input) throws IllegalArgumentException {
        String[] parameters = input.split(" \\| ", NUMBER_OF_DEADLINE_PARAMETERS);

        if (parameters.length != NUMBER_OF_DEADLINE_PARAMETERS) {
            throw new IllegalArgumentException(
                    String.format("Unable to parse deadline from file input. Expected %d parameters, got %d",
                            NUMBER_OF_DEADLINE_PARAMETERS,
                            parameters.length));
        }

        // deconstruct array elements into their respective attributes
        boolean isComplete = parameters[0].equals("1");
        String description = parameters[1];
        LocalDateTime by = DateParser.parseFileInputDate(parameters[2]);

        return new Deadline(description, isComplete, by);
    }

    /**
     * Creates and returns an {@link Event} object based on the
     * input information provided.
     *
     * @param input a string containing information about the {@link Event} object
     * @return an {@link Event} object
     * @throws IllegalArgumentException if the input is invalid
     * @see Event
     */
    private Event createEventTask(String input) throws IllegalArgumentException {
        String[] parameters = input.split(" \\| ", NUMBER_OF_EVENT_PARAMETERS);

        if (parameters.length != NUMBER_OF_EVENT_PARAMETERS) {
            throw new IllegalArgumentException(
                    String.format("Unable to parse event from file input. Expected %d parameters, got %d",
                            NUMBER_OF_EVENT_PARAMETERS,
                            parameters.length));
        }

        // deconstruct array elements into their respective attributes
        boolean isComplete = parameters[0].equals("1");
        String description = parameters[1];
        LocalDateTime from = DateParser.parseFileInputDate(parameters[2]);
        LocalDateTime to = DateParser.parseFileInputDate(parameters[3]);

        return new Event(description, isComplete, from, to);
    }

    /**
     * Creates and returns a {@link Todo} object based on the
     * input information provided.
     *
     * @param input a string containing information about the {@link Todo} object
     * @return a {@link Todo} object
     * @see Todo
     */
    private Todo createTodoTask(String input) {
        String[] parameters = input.split(" \\| ", NUMBER_OF_TODO_PARAMETERS);

        if (parameters.length != NUMBER_OF_TODO_PARAMETERS) {
            throw new IllegalArgumentException(
                    String.format("Unable to parse todo from file input. Expected %d parameters, got %d",
                            NUMBER_OF_TODO_PARAMETERS,
                            parameters.length));
        }

        // deconstruct array elements into their respective attributes
        boolean isComplete = parameters[0].equals("1");
        String description = parameters[1];

        return new Todo(description, isComplete);
    }
}
