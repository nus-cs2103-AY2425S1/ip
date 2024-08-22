public class StrandDescNotFoundException extends StrandException{
    protected String part;
    protected String task;
    public StrandDescNotFoundException(String s, String task) {
        this.part = s;
        this.task = task;
    }
    @Override
    public String toString() {
        return this.part + " not found " + super.toString()
                + "\nPlease include a " + this.part +" for this " + this.task + " task ";
    }
}
