public abstract class Task {
    protected String description;
    protected boolean isDone = false;
    protected String typeOfTask = " ";

    public void markAsDone() {
        this.isDone = true;
    }

    protected void setTypeOfTask(String typeOfTask) {
        this.typeOfTask = typeOfTask;
    }

    protected void setDescription(String description) {
        this.description = description;
    }

    public String saveFileFormat(String delimiter) {
        return String.format("%s%s%s%s%s", typeOfTask, delimiter, isDone ? "1" : "0", delimiter, description);
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s", 
                typeOfTask, this.isDone ? "X" : " ", this.description);
    }
}