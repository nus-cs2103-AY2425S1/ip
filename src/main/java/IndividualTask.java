abstract public class IndividualTask {
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

    public String getSaveIcon() {
        if (this.isTaskDone) {
            return "1";
        }
        return "0";
    }

    public void markOrUnmark(String msg) {
        this.isTaskDone = msg.equals("mark");
    }

    public String getTaskDescription() {
        return this.taskDescription;
    }

    public abstract String saveToFileFormat();

    @Override
    public String toString() {
        if (!this.getIcon().isEmpty()) {
            return  "[" + this.getIcon() + "] " + this.getTaskDescription();
        }
        return   "[ ] " + this.getTaskDescription();
    }
}

