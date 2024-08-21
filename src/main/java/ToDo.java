class Todo extends Task {

    public Todo(String todo){
        super(todo, TaskType.TODO);
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