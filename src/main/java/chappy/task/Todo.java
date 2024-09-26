package chappy.task;

import org.json.simple.JSONObject;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a formatted String containing values of this Todo.
     *
     * @return String containing values of this Todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns this Todo as a JSONObject.
     *
     * @return JSONObject (containing a Todo).
     */
    @Override
    public JSONObject toJson() {
        JSONObject j = new JSONObject();
        j.put("type", "todo");
        j.put("description", this.description);
        return j;
    }
    
    /**
     * Returns the Todo object stored in the JSONObject.
     *
     * @param jsonObject JSONObject (containing a Todo).
     * @return Todo object.
     */
    public static Task fromJson(JSONObject jsonObject) {
        Todo t = new Todo(jsonObject.get("description").toString());
        return t;
    }
}