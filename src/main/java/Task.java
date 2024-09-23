import org.json.simple.JSONObject;

public class Task {
    protected String description;
    protected boolean isDone;


    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
        System.out.println("Fantastic! I've marked this task as done!");
        System.out.println(this.toString());
    }

    public void markAsNotDone() {
        this.isDone = false;
        System.out.println("Aww.. I've marked this task as not done yet..");
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }

  

    public JSONObject toJson() {
        JSONObject j = new JSONObject();
        j.put("type", "task");
        j.put("description", this.description);
        return j;
    }
    //...
}