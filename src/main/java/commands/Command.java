package commands;

/**
 * A basic command class with only one field response
 */
public class Command {

    //protected String commandType;
    private String response;
    private boolean isGoodbye;
    /**
     * Constructor for command with the specified response.
     *
     * @param response The type of command.
     */
    public Command (String response) {
        this.response = response;
        this.isGoodbye = false;
    }
    public boolean isTypeGoodbye() {
        return this.isGoodbye;
    }

    protected void setAsGoodbye() {
        this.isGoodbye = true;
    }

    public String getResponse() {
        return this.response;
    }

}
