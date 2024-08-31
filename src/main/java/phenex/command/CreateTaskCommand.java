package phenex.command;

public abstract class CreateTaskCommand extends Command {
    protected String name;

    public CreateTaskCommand(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
