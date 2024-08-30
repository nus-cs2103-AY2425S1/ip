package impl.interfaces;

public class Deadlines extends Task{
    protected String by;
    /**
     * @inheritDoc
     * Stores due-date of deadline.
     *
     * @param by due-date of deadline.
     */
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
        return "deadline " + this.description + " /by " + this.by + " | " + this.isDone;
    }

}
