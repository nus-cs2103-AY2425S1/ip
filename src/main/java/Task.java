/**
 * This class represents a task.
 * It provides contains the name of the task.
 */
public class Task {
    private String name;
    public Task(String name) {
        this.name = name;
    }

    public String printTask() {
        return name;
    }
}
