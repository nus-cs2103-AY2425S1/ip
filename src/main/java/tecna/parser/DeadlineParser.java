package tecna.parser;

import java.time.LocalDateTime;

import org.json.simple.JSONObject;

import tecna.exception.JsonLoadingException;

import tecna.task.Deadline;
import tecna.exception.JsonLoadingExceptionType;
import tecna.util.DateTimeUtil;

/**
 * Parses the task of Deadline type from a JSONObject.
 *
 * @author DennieDan.
 */
public class DeadlineParser {

    /**
     * Parses a JSONObject into a Deadline.
     *
     * @param jsonObject A json object with the value of "type" is "deadline".
     * @return A Deadline instance.
     * @throws JsonLoadingException When there is error parsing some attributes of Deadline.
     */
    public Deadline parse(JSONObject jsonObject) throws JsonLoadingException {
        String deadline = (String) jsonObject.get("by");
        String taskName = (String) jsonObject.get("taskName");
        Boolean isDone = (Boolean) jsonObject.get("isDone");

        if (deadline == null || taskName == null || isDone == null) {
            throw new JsonLoadingException(JsonLoadingExceptionType.DEADLINE_DATA_MISSING);
        }

        LocalDateTime by =
        DateTimeUtil.parseDateTime(deadline);
        return new Deadline(taskName, isDone, by);
    }
}
