public class StrandDescNotFoundException extends StrandException{
    protected String part;
    public StrandDescNotFoundException(String s) {
        this.part = s;
    }
    @Override
    public String toString() {
        return this.part + " not found " + super.toString()
                + "\nPlease include a " + this.part +" for this task";
    }
}
