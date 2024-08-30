package tecna;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.json.simple.JSONObject;

public class EventParser {
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

