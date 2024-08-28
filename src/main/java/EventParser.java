import org.json.simple.JSONObject;

public class EventParser {
    public Event parse(JSONObject jsonObject) {
        return new Event((String) jsonObject.get("taskName"), (Boolean) jsonObject.get("isDone"), (String) jsonObject.get("from"), (String) jsonObject.get("to"));
    }
}

