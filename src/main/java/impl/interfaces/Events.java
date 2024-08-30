package impl.interfaces;

public class Events extends Task{
    protected String to;
    protected String from;
    public Events(String description, String to, String from){
        super(description);
        this.to = to;
        this.from = from;
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + " (from: " + this.from + " to " + this.to + ")";
    }

    @Override
    public String loadString(){
        return "event" + this.description + "/from " + this.from + " /to " + this.to + " | " + this.isDone;
    }

}
