package impl.interfaces;

public class Deadlines extends Task{
    protected String by;
    public Deadlines(String description, String by){
        super(description);
        this.by = by;
    }

    @Override
    public String toString(){
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }

    @Override
    public String loadString(){
        return "deadline" + this.description + "/by " + this.by + " | " + this.isDone;
    }

}
