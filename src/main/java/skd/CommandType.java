package skd;

import skd.task.Task;
import skd.task.ToDo;
import skd.task.Deadline;
import skd.task.Event;
import skd.task.TaskType;
public enum CommandType {
    BYE,
    LIST,
    MARK,
    UNMARK,
    TODO,
    DEADLINE,
    EVENT,
    DELETE,
    ETC;
}