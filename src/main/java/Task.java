public class Task {
    String description;
    public enum TaskType {
        TODO,
        EVENT,
        DEADLINE,
    }

    private boolean completed;
    Task(String task){
        this.description = task;
        this.completed = false;
    }
    public static Task of(String task, TaskType type) {
        switch (type) {
            case DEADLINE:
                String[] taskArray = task.split("/", 2);
                if (taskArray.length == 2){
                    String[] by = taskArray[1].split(" ", 2);
                    if (by[0].equalsIgnoreCase("by") && by.length == 2) {
                        return new Deadline(taskArray[0], by[1]);
                    }
                }
                throw new RuntimeException();

            case EVENT:
                String[] taskArr = task.split("/", 3);
                if (taskArr.length == 3){
                    String[] from = taskArr[1].split(" ", 2);
                    String[] to = taskArr[2].split(" ", 2);
                    if (from[0].equalsIgnoreCase("from") && from.length == 2 && to[0].equalsIgnoreCase("to") && to.length == 2) {
                        return new Event(taskArr[0], from[1], to[1]);
                    }
                }
                throw new RuntimeException();
            case TODO:
                return new Todo(task);
            default:
                return new Task(task);
        }

    }
    public void setDescription(String description) {
        this.description = description;
    }

    public void mark() {
        this.completed = true;
    }

    public void unmark() {
        this.completed = false;
    }

    public String getStatusIcon() {
        return this.completed ? "X" : " ";
    }
    public String getTypeIcon() {
        return " ";
    }

    public boolean getStatus() {
        return this.completed;
    }

    @Override
    public String toString() {
        return this.description;
    }
}
