package infinity.task;

/**
 * This abstract class are the tasks that the bot will recognise and manage.
 */
public abstract class Task {
    /** Description of the Task */
    protected String description;
    /** Status of the Task, whether it is marked */
    protected boolean isDone = false;
    /** Type of Task */
    protected char typeOfTask;

    /**
     * Sets the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Sets the type of task
     * 
     * @param typeOfTask The type of task, it will set the first letter to the type.
     */
    protected void setTypeOfTask(String typeOfTask) {
        this.typeOfTask = typeOfTask.charAt(0);
    }

    /**
     * Sets the description of the task.
     * 
     * @param description The description of the task.
     */
    protected void setDescription(String description) {
        this.description = description;
    }

    /**
     * Parses the save-file format of the task.
     * 
     * @param delimiter The delimiter to separate the fields.
     * @return The save-file format of the task.
     */
    public String saveFileFormat(String delimiter) {
        return String.format("%s%s%s%s%s", Character.toString(typeOfTask), delimiter, isDone ? "1" : "0", delimiter, description);
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s", 
                Character.toString(typeOfTask), this.isDone ? "X" : " ", this.description);
    }
}