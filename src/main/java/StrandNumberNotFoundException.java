public class StrandNumberNotFoundException extends StrandException{
    protected String command;
    public StrandNumberNotFoundException(String command) {
        this.command = command;
    }
    @Override
    public String toString() {
        return "Index for task not found " + super.toString()
                + "Please input a number after " + this.command
                + ". (e.g. "  + this.command + " 1)";
    }
}
