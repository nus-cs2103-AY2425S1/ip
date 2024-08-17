public class Task {
    private static int numberOfTasks = 0;
    private String description;
    private boolean isDone;

    protected Task(String description) {
        this.description = description;
        this.isDone = false;
        numberOfTasks++;
    }
    
    public static ToDo createTask(String description) {
        return new ToDo(description);
    }

    public static Deadline createTask(String description, String by) {
        return new Deadline(description, by);
    }

    public static Event createTask(String description, String from, String to) {
        return new Event(description, from, to);
    }

    public void markDone() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.print("  ");
        System.out.println(this);
    }

    public void unmarkDone() {
        this.isDone = false;
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.print("  ");
        System.out.println(this);
    }

    private String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public int getNumberOfTasks() {
        return numberOfTasks;
    }

    @Override
    public String toString() {
        String output = "[" + getStatusIcon() + "] ";
        output += description;
        return output;
    }
}
