public class IndividualTask {
    private String taskDescription;
    private boolean isTaskDone = false;

    public IndividualTask(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getIcon() {
        if (this.isTaskDone) {
            return "X";
        }
        return "";
    }

    public void markOrUnmark(String msg) {
        this.isTaskDone = msg.equals("mark");
    }

    public String getTaskDescription() {
        return this.taskDescription;
    }

    @Override
    public String toString() {
        if (!this.getIcon().isEmpty()) {
            return  "[" + this.getIcon() + "] " + this.getTaskDescription();
        }
        return   "[ ] " + this.getTaskDescription();
    }
}

