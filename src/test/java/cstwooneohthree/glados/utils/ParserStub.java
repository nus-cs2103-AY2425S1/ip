package cstwooneohthree.glados.utils;

import java.time.LocalDate;

import cstwooneohthree.glados.enums.TaskType;
import cstwooneohthree.glados.exceptions.GladosException;
import cstwooneohthree.glados.exceptions.TaskNotFoundException;

/** To be used for unit testing in TastList.java */
public class ParserStub {
    /**
     * Parses a task based on its taskType and command line arguments.
     *
     * @param taskType Type of task.
     * @param input Command line arguments passed by user.
     * @return ParsedInfo object with task description and corresponding dates.
     * @throws GladosException If arguments cannot be parsed correctly.
     */
    public static ParsedInfo parseTask(TaskType taskType, String input) throws GladosException {

        assert taskType == TaskType.TODO || taskType == TaskType.DEADLINE || taskType == TaskType.EVENT;

        switch (taskType) {
        case TODO:
            return new ParsedInfo("test todo", null);
        case DEADLINE:
            return new ParsedInfo("test deadline", new LocalDate[]{
                    LocalDate.parse("2025-08-19")});
        case EVENT:
            return new ParsedInfo("test event", new LocalDate[]{
                    LocalDate.parse("2025-08-19"), 
                    LocalDate.parse("2025-08-19")});
        default:
            throw new TaskNotFoundException();
        }
    }
}
