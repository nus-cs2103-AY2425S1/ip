package tecna;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.json.simple.JSONObject;

public class DeadlineParser {
    public Deadline parse(JSONObject jsonObject) {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime by = LocalDateTime.parse(jsonObject.get("by").toString(), pattern);
        return new Deadline((String) jsonObject.get("taskName"), (Boolean) jsonObject.get("isDone"), by);
    }
}
