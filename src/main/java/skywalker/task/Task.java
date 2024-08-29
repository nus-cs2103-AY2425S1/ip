package skywalker.task;


public class Task {
    public String description;
    public boolean isDone;
    protected TaskType taskType;

    /**
     * Constructs a Task with the specified description and task type.
     * The task is initialised to be undone.
     * @param description
     * @param taskType
     */
    public Task(String description, TaskType taskType) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
    }

    /**
     * Returns a 'X' if the task is done and empty space otherwise.
     * @return "X" if the task is done, otherwise a space character " "
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Mark the task as done if undone
     * Else print a statement to say that it is alr done.
     */
    public void markDone(){
        if(!this.isDone) {
            this.isDone = true;
        } else{
            System.out.println("the task is done liao");
        }
    }

    /**
     * Mark the task as undone if done
     * Else print a statement to say that it is alr undone.
     */
    public void unmarkDone() {
        if(this.isDone) {
            this.isDone = false;
        } else {
            System.out.println("the task is alr not done");
        }
    }

    /**
     * Returns a string representation of the task, including its status icon
     * and description.
     *
     * @return A string representation of the task if the "[X] description"
     */
    @Override
    public String toString(){
        return "[" + getStatusIcon()+ "] " + description;
    }
}
