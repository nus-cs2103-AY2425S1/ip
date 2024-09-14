public abstract class Command {
    private String description;

    public Command(String description) {
        this.description = description;
    }

    public abstract void execute();
}
