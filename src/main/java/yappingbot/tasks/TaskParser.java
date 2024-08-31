package yappingbot.tasks;

import yappingbot.exceptions.YappingBotInvalidSaveFileException;
import yappingbot.stringconstants.ReplyTextMessages;

public class TaskParser {
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
