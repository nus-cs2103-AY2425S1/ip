package rotodo.tasklist;

/**
 * The Task class encapsulates a Task that has
 * a name, and has a 'done' state.
 * 
 * @author Ng Kay Hian
 * @version CS2103T AY24/25 Semester 1
 */
public abstract class Task {
    String name;
    boolean done = false;
    
    public Task(String value, String done) {
        this.name = value;
        this.done = (done == "1");
    }

    /**
     * Marks a task as done, and reports the task 
     * current (new) 'done' state.
     * 
     * @return task current state
     */
    public String markAsDone() {
        done = true;
        return "    RoTodo is happy for you! Task done:\n      " + this.toString();
    }

    /**
     * Unmarks a task as done, and reports the task 
     * current (new) 'done' state.
     * 
     * @return task current state
     */
    public String unmarkAsDone() {
        done = false;
        return "    Did something happen? RoTodo is confused... Task undone:\n      " + this.toString();
    }

    @Override
    public String toString() {
        return "[" + (done ? "X" : " ") + "] " + name; 
    }

    public abstract String saveString();

    public String saveString(String insert) {
        return name + " | " + (insert == "" ? "" : insert + " | ") + (done ? "1" : "0");
    }
}
