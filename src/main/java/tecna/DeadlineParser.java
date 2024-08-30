package tecna;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.json.simple.JSONObject;

public class DeadlineParser {
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
