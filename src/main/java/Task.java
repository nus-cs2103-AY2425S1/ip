public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }
    @Override
    public String toString(){ return this.description; }
    public String listedString(){ return this.toString(); }
    public String classFirstChar() {return "["+this.getClass().toString().charAt(6)+"]";}
    public boolean isDone() {return this.isDone;}
    public void setUndone(){ this.isDone = false;}
    public void setDone(){ this.isDone = true;}
}