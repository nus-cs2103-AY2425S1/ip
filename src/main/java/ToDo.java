/**
 * represents a task of type "Todo", which has no specific date or deadline.
 * A Todo task simply contains a description and its completion status.
 */
class Todo extends Task {

    /**
     * Constructs a Todo task with the specified description.
     *
     * @param todo The description of the Todo task.
     */
    public Todo(String todo){
        super(todo, TaskType.TODO);
    }

    /**
     * Returns the type of this task, which is TaskType.TODO.
     *
     * @return The TaskType of the task, specifically TaskType.TODO.
     */
    @Override
    public TaskType type() {
        return TaskType.TODO;
    }

    /**
     * Returns a string representation of the Todo task.
     * The format includes the task type, status icon, and description.
     *
     * @return A string in the format "[TaskType][StatusIcon] Description".
     */
    @Override
    public String toString(){
        return " ["+this.type()+"]["+this.getStatusIcon()+"] "+ this.getDescription();
    }

}