class Todo extends Task {
    private boolean isDone;

    public Todo(String todo){
        super(todo);
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
    public void unmarkTask(){
        this.isDone = false;
    }

    public String type(){
        return "T";
    }




}