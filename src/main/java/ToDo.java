class Todo extends Task {
    private boolean isDone;

    public Todo(String todo){
        super(todo, TaskType.TODO);
    }

    public boolean isDone(){
        return this.isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    public void setDone(boolean bool){
        this.isDone = bool;
    }
    public void markAsDone(){
        this.isDone = true;
    }

    public String getDescription(){
        return this.description;
    }

    public void unmarkTask(){
        this.isDone = false;
    }
    // Method to return TaskType
    @Override
    public TaskType type() {
        return TaskType.TODO;
    }


    @Override
    public String toString(){
        return " ["+this.type()+"]["+this.getStatusIcon()+"] "+ this.getDescription();
    }

}