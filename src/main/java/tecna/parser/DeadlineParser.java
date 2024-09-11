package tecna.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.json.simple.JSONObject;

import tecna.exception.JsonLoadingException;

import tecna.task.Deadline;
import tecna.exception.JsonLoadingExceptionType;

/**
 * Parses the task of Deadline type from a JSONObject.
 *
 * @author DennieDan.
 */
public class DeadlineParser {

    /**
     * Parses a JSONObject into a Deadline.
     * @param jsonObject is a json object with the value of "type" is "deadline".
     * @return a Deadline instance.
     * @throws JsonLoadingException when there is error parsing some attributes of Deadline.
     */
    public Deadline parse(JSONObject jsonObject) throws JsonLoadingException {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        String deadline = (String) jsonObject.get("by");
        String taskName = (String) jsonObject.get("taskName");
        Boolean isDone = (Boolean) jsonObject.get("isDone");

        if (deadline == null || taskName == null || isDone == null) {
            throw new JsonLoadingException(JsonLoadingExceptionType.DEADLINE_DATA_MISSING);
        }

        LocalDateTime by = LocalDateTime.parse(deadline, pattern);
        return new Deadline(taskName, isDone, by);
    }
}
