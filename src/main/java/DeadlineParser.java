import org.json.simple.JSONObject;

public class DeadlineParser {
    public Deadline parse(JSONObject jsonObject) {
        return new Deadline((String) jsonObject.get("taskName"), (Boolean) jsonObject.get("isDone"), (String) jsonObject.get("by"));
    }
}
