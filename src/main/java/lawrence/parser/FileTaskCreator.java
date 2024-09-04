package lawrence.parser;

import lawrence.task.Task;
import lawrence.task.TaskType;
import lawrence.task.Todo;
import lawrence.task.Deadline;
import lawrence.task.Event;
import lawrence.utils.DateParser;

import java.time.LocalDateTime;

public class FileTaskCreator implements TaskCreator {
    private static final int NUMBER_OF_TODO_PARAMETERS = 2;
    private static final int NUMBER_OF_DEADLINE_PARAMETERS = 3;
    private static final int NUMBER_OF_EVENT_PARAMETERS = 4;

    @Override
    public Task createTask(String input) {
        // separate the string containing information about the task type into index 0
        String[] inputComponents = input.split(" \\| ", 2);

        // parse the type of task that needs to be created
        TaskType type = TaskType.fromString(inputComponents[0]);

        Task t;
        switch (type) {
        case TODO:
            t = createTodoTask(inputComponents[1]);
            break;
        case DEADLINE:
            t = createDeadlineTask(inputComponents[1]);
            break;
        case EVENT:
            t = createEventTask(inputComponents[1]);
            break;
        default:
            throw new IllegalArgumentException("Unknown task type: " + type);
        }
        return t;
    }

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

    private Deadline createDeadlineTask(String input) {
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

    private Event createEventTask(String input) {
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
}
