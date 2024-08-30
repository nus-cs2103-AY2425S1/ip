package tecna;

import org.json.simple.JSONObject;

public class ToDoParser {
    public ToDo parse(JSONObject jsonObject) {
        return new ToDo((String) jsonObject.get("taskName"), (Boolean) jsonObject.get("isDone") );

    }
}
