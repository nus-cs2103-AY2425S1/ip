package yappingbot.tasks.tasklist;

import yappingbot.exceptions.YappingBotInvalidSaveFileException;
import yappingbot.stringconstants.ReplyTextMessages;
import yappingbot.tasks.Deadline;
import yappingbot.tasks.Event;
import yappingbot.tasks.Task;
import yappingbot.tasks.Todo;


/**
 * TaskParser class for parsing strings to tasks.
 */
public class TaskParser {

    /**
     * Parse String array to determine which tasks to be created, creates them, and passes vales to be deserialized.
     *
     * @param s String array of String split by colon (:) that hold data for tasks to deserialize.
     * @return Task based on the given String array.
     * @throws YappingBotInvalidSaveFileException If an invalid task type is detected or unable to determine task type.
     */
    public static Task parseSingleTask(String[] s) throws YappingBotInvalidSaveFileException {
        Task t;
        try {
            switch (TaskTypes.valueOf(s[0])) {
            case TODO:
                t = new Todo();
                t.deserialize(s);
                break;
            case DEADLINE:
                t = new Deadline();
                t.deserialize(s);
                break;
            case EVENT:
                t = new Event();
                t.deserialize(s);
                break;
            default:
                throw new YappingBotInvalidSaveFileException(String.format(
                        ReplyTextMessages.INVALID_SAVE_FILE_EXCEPTION_INVALID_VALUES_1s, s[0]
                                                                          )
                );
            }
        } catch (Exception e) {
            // todo: add line number
            throw new YappingBotInvalidSaveFileException(String.format(
                    ReplyTextMessages.INVALID_SAVE_FILE_EXCEPTION_INVALID_VALUES_1s, e.getMessage()
                                                                      )
            );
        }
        return t;
    }
}
