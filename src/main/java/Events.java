public class Events extends Task{
    protected String by;
    protected String from;
    public Events(String description, String by, String from){
        super(description);
        this.by = by;
        this.from = from;
    }

    @Override
    public String toString(){
        return "[D]" + super.toString() + " (from: " + this.from + " to " + this.by + ")";
    }

}
