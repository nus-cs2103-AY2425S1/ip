package chappy.task;

import org.json.simple.JSONObject;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public JSONObject toJson() {
        JSONObject j = new JSONObject();
        j.put("type", "todo");
        j.put("description", this.description);
        return j;
    }

    public static Task fromJson(JSONObject jsonObject) {
        Todo t = new Todo(jsonObject.get("description").toString());
        return t;
    }
}