public class Task {
    private String name;
    private Boolean isCompleted;


    public Task(String name) {
        this.name = name;
        this.isCompleted = false;
    }


    public void markCompleted() {
        this.isCompleted = true;
    }

    public void markIncomplete() {
        this.isCompleted = false;
    }
    public static Task deadlineTask(String name, String deadline) {
        return new DeadlineTask(name, deadline);
    }
    public static Task eventTask(String name, String startTime, String endTime) {
        return new EventTask(name, startTime, endTime);
    }
    public static Task toDoTask(String name) {
        return new ToDoTask(name);
    }

    @Override
    public String toString() {
        String completedStatus = "";
        if (!isCompleted) {
            completedStatus += "[ ] ";
        } else {
            completedStatus += "[X] ";
        }
        return String.format(completedStatus + name);
    }
}
