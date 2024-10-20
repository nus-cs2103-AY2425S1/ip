package darwin.parser;

import darwin.exception.IllegalTaskArgumentException;
import darwin.exception.IllegalTaskTypeException;
import darwin.task.Task;
import darwin.task.TaskType;

import java.util.HashMap;

/**
 * TaskFactory class to create Task instances from user input or database string.
 */
public class TaskFactory {
    private static final HashMap<TaskType, TaskParser> TASK_TYPE_PARSER_MAP = new HashMap<>() {{
        put(TaskType.TODO, new ToDoParser());
        put(TaskType.DEADLINE, new DeadlineParser());
        put(TaskType.EVENT, new EventParser());
    }};

    /**
     * Creates a Task instance from user input of corresponding type.
     * @param taskTypeStr task type
     * @param taskArgs task arguments
     * @return Task based on input
     * @throws IllegalTaskTypeException if task type is invalid
     * @throws IllegalTaskArgumentException if task arguments are invalid
     */
    public static Task userInputToTask(String taskTypeStr, String taskArgs) throws IllegalTaskTypeException, IllegalTaskArgumentException {
        TaskType taskType = TaskType.fromName(taskTypeStr);
        TaskParser parser = TASK_TYPE_PARSER_MAP.get(taskType);
        return parser.parse(taskArgs);
    }

    /**
     * Creates a Task instance from database string.
     * @param taskStr task string from database
     * @return Task based on input
     * @throws IllegalTaskTypeException if task type is invalid
     * @throws IllegalTaskArgumentException if task arguments are invalid
     */
    public static Task dbToTask(String taskStr) throws IllegalTaskTypeException, IllegalTaskArgumentException {
        String[] args = taskStr.split(",");
        TaskType taskType = TaskType.fromSymbol(args[0]);
        TaskParser parser = TASK_TYPE_PARSER_MAP.get(taskType);
        return parser.parseFromDb(taskStr);
    }
}
