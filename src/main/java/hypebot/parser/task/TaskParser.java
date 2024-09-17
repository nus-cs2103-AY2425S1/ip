package hypebot.parser.task;

import hypebot.exception.datetime.DueDateParseException;
import hypebot.exception.datetime.EventDateTimeParseException;
import hypebot.exception.illegal.IllegalTaskTypeException;
import hypebot.exception.missing.MissingArgumentException;
import hypebot.exception.missing.MissingTaskNameException;
import hypebot.exception.illegal.DatePassedException;
import hypebot.parser.datetime.DateTimeParser;
import hypebot.parser.Parser;
import hypebot.task.Deadline;
import hypebot.task.Event;
import hypebot.task.ToDo;

public abstract class TaskParser extends Parser {
    protected final int taskTypeArrayIdx = 0;
    protected final int taskCompletedArrayIdx = 1;
    protected final int taskNameArrayIdx = 2;
    protected final int dueDateArrayIdx = 3;
    protected final int startTimeArrayIdx = 3;
    protected final int endTimeArrayIdx = 4;
    protected DateTimeParser dateTimeParser;
    protected enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }

    public TaskParser(DateTimeParser dateTimeParser) {
        this.dateTimeParser = dateTimeParser;
    }

    protected abstract String[] splitLine(String line);

    protected abstract TaskType extractTaskType(String line) throws IllegalTaskTypeException;

    protected abstract String parseTaskName(String line) throws MissingTaskNameException;

    protected abstract ToDo parseToDo(String line) throws MissingTaskNameException;

    protected abstract Deadline parseDeadline(String fullCommand)
            throws MissingArgumentException, DueDateParseException, DatePassedException;

    protected abstract Event parseEvent(String fullCommand)
                    throws MissingArgumentException, EventDateTimeParseException, DatePassedException;
}
