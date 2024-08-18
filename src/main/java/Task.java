public class Task {
    private String desc;
    private String isDone;
    private static int totalTasks = 0;

    public Task(String desc) {
        this.desc = desc;
        this.isDone = "[ ]";
        totalTasks++;
    }

    public void mark() {
        this.isDone = "[X]";
    }

    public void unmark() {
        this.isDone = "[ ]";
    }

    public static int getTotalTasks() {
        return totalTasks;
    }

    public static void setTotalTasks(int totalTasks) {
        Task.totalTasks = totalTasks;
    }

    @Override
    public String toString() {
        return this.isDone + " " + this.desc;
    }
}
