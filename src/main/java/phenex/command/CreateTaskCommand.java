package phenex.command;

public abstract class CreateTaskCommand extends Command {
    /** encapsulates the name of the task to be created */
    protected String name;

    public CreateTaskCommand(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
