package darwin.parser;

import darwin.exception.IllegalTaskArgumentException;
import darwin.exception.IllegalTaskTypeException;
import darwin.task.Task;
import darwin.task.TaskType;

import java.util.HashMap;

public class TaskFactory {

    private static final HashMap<TaskType, TaskParser> TASK_TYPE_PARSER_MAP = new HashMap<>() {{
        put(TaskType.TODO, new ToDoParser());
        put(TaskType.DEADLINE, new DeadlineParser());
        put(TaskType.EVENT, new EventParser());
    }};

    public static Task userInputToTask(String taskTypeStr, String taskArgs) throws IllegalTaskTypeException, IllegalTaskArgumentException {
        TaskType taskType = TaskType.fromName(taskTypeStr);
        TaskParser parser = TASK_TYPE_PARSER_MAP.get(taskType);
        return parser.parse(taskArgs);
    }

    public static Task dbToTask(String taskStr) throws IllegalTaskTypeException, IllegalTaskArgumentException {
        String[] args = taskStr.split(",");
        TaskType taskType = TaskType.fromSymbol(args[0]);
        TaskParser parser = TASK_TYPE_PARSER_MAP.get(taskType);
        return parser.parseFromDb(taskStr);
    }
}
