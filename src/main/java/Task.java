package main.java;

public class Task {
    private Boolean done;
    private String desc;
    private Category taskType;

    private enum Category {
        T,
        D,
        E
    }

    public Task(String desc) {
        this.desc = desc;
        this.done = false;
    }

    public Task(char c, String desc) {
        this.desc = desc;
        this.done = false;
        if (c == 'T') {
            this.taskType = Category.T;
        } else if (c == 'D') {
            this.taskType = Category.D;
        } else if (c == 'E') {
            this.taskType = Category.E;
        }
    }

    /**
     * Marks this task as done/completed.
     */
    public void markDone() {
        this.done = true;
    }

    /**
     * Marks this task as not done/completed.
     */
    public void markUndone() {
        this.done = false;
    }

    /**
     * Returns description of task.
     * @return task description
     */
    public String getDesc() {
        return done.equals(true) ? "[" + taskType.name() + "]"+ "[X] " + this.desc
                                 : "[" + taskType.name() + "]"+ "[ ] " + this.desc;
    }

}
