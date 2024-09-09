package tecna;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.json.simple.JSONObject;

/**
 * Parses the task of Event type from a JSONObject.
 *
 * @author DennieDan.
 */
public class EventParser {

    /**
     * Parses a JSONObject into an Event instance.
     *
     * @param jsonObject is a json object with the value of "type" is "event".
     * @return an Event instance.
     * @throws JsonLoadingException when there is error parsing some attributes of Event.
     */
    public Event parse(JSONObject jsonObject) throws JsonLoadingException {
        String start = jsonObject.get("from").toString();
        String end = jsonObject.get("to").toString();
        String taskName = jsonObject.get("taskName").toString();
        Boolean isDone = (Boolean) jsonObject.get("isDone");

        if (start == null || end == null || taskName == null || isDone == null) {
            throw new JsonLoadingException(JsonLoadingExceptionType.EVENT_DATA_MISSING);
        }

        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime from = LocalDateTime.parse(start, pattern);
        LocalDateTime to = LocalDateTime.parse(end, pattern);
        return new Event(taskName,isDone, from, to);
    }
}

