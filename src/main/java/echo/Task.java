package echo;
/**
 * The Task class represents a general task with a description and completion status.
 * It serves as an abstract base class for specific types of tasks like ToDos, Deadlines, and Events.
 */
public abstract class Task {
    boolean isDone;
    String taskDes;


    /**
     * Constructs a Task with the specified description.
     * The task is initially not done.
     *
     * @param taskDes The description of the task.
     */
    public Task(String taskDes){
        this.taskDes = taskDes;
        isDone = false;
    }

    /**
     * Marks the task as done.
     */
    public void setDone() {
        isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void setUndone() {
        isDone = false;
    }

    /**
     * Returns a string representation of the task, including its completion status.
     *
     * @return A string that shows whether the task is done and its description.
     */
    public String toString() {
        if(isDone){
            return "[X] " + taskDes;
        } else {
            return "[ ] " + taskDes;
        }
    }

    /**
     * Returns a string representing the completion status of the task.
     *
     * @return "1" if the task is done, otherwise "0".
     */
    public String getIsDone() {
        if(isDone) {
            return "1";
        }
        return "0";
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public  String getTaskDes() {
        return taskDes;
    }

    /**
     * Returns additional information about the task.
     * This method is abstract and must be implemented by subclasses.
     *
     * @return A string containing additional task information.
     */
    public abstract String getAdd();

    /**
     * Returns the type letter of the task (e.g., "T" for ToDo, "D" for Deadline, "E" for Event).
     * This method is abstract and must be implemented by subclasses.
     *
     * @return A string containing the type letter of the task.
     */
    public abstract String getTypeLetter();

    public abstract String editTask(String input);
}