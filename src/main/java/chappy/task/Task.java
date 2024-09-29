package chappy.task;

import org.json.simple.JSONObject;

public class Task {
    protected String description;
    protected boolean isDone;


    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns String representing if task is done. "X" if task is done, " " otherwise.
     *
     * @return String .
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks this task as done (sets isDone to true).
     *
     */
    public String markAsDone() {
        this.isDone = true;
        return "Fantastic! I've marked this task as done!\n" + this.toString();
    }

    /**
     * Marks this task as not done (sets isDone to false).
     *
     */
    public String markAsNotDone() {
        this.isDone = false;
        return "Aww.. I've marked this task as not done yet..\n" + this.toString();
    }

    /**
     * Returns a formatted String containing values of this Task.
     *
     * @return String containing values of this Task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }

    /**
     * Returns this Task as a JSONObject.
     *
     * @return JSONObject (containing a Task).
     */
    public JSONObject toJson() {
        JSONObject j = new JSONObject();
        j.put("type", "task");
        j.put("description", this.description);
        j.put("done", Boolean.toString(isDone));
        return j;
    }

    /**
     * Returns the Task object stored in the JSONObject.
     *
     * @param jsonObject JSONObject (containing a Task).
     * @return Task object.
     */
    public static Task fromJson(JSONObject jsonObject) {
        Task t = new Task(jsonObject.get("description").toString());
        if (Boolean.parseBoolean(jsonObject.get("done").toString())) {
            t.markAsDone();
        }
        return t;
    }

}
