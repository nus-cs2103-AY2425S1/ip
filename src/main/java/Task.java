public class Task {
    private String name;
    private Boolean isCompleted;

    public Task (String name) {
        this.name = name;
        this.isCompleted = false;
    }

    public String setCompleted(Boolean completed) throws ChatterBoxMarkError {
        StringBuilder message = new StringBuilder();
        if (!this.isCompleted && completed) {
            this.isCompleted = true;
            message.append("____________________________________________________________\n");
            message.append("Nice! I've marked this task as done: ");
            message.append(this).append("\n");
            message.append("____________________________________________________________\n");
        } else if (this.isCompleted && !completed) {
            this.isCompleted = false;
            message.append("____________________________________________________________\n");
            message.append("Ok, I've marked this task as not done yet: ");
            message.append(this).append("\n");
            message.append("____________________________________________________________\n");
        } else if (this.isCompleted && completed) {
            message.append("____________________________________________________________\n");
            message.append("This task is already completed: ");
            message.append(this).append("\n");
            message.append("____________________________________________________________\n");
        } else {
            message.append("____________________________________________________________\n");
            message.append("This task is not completed: ");
            message.append(this).append("\n");
            message.append("____________________________________________________________\n");
        }
        return message.toString();
    }

    public Boolean getCompleted() {
        return isCompleted;
    }

    public String getName() {
        return name;
    }

    public String storeTask() {
        StringBuilder saveTaskInfo = new StringBuilder();
        if (this.getCompleted()) {
            saveTaskInfo.append("done, ");
        }
        else {
            saveTaskInfo.append("undone, ");
        }
        saveTaskInfo.append("task ");
        saveTaskInfo.append(this.getName());
        saveTaskInfo.append("\n");
        return saveTaskInfo.toString();
    }

    @Override
    public String toString() {
        return this.isCompleted ? "[X] " + this.name : "[ ] " + this.name;
    }
}
