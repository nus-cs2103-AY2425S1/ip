package tecna;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.json.simple.JSONObject;

public class EventParser {
    public Event parse(JSONObject jsonObject) {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime from = LocalDateTime.parse(jsonObject.get("from").toString(), pattern);
        LocalDateTime to = LocalDateTime.parse(jsonObject.get("to").toString(), pattern);
        return new Event((String) jsonObject.get("taskName"), (Boolean) jsonObject.get("isDone"), from, to);
    }
}

