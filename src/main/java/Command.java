abstract class Command {
    protected Calebyyy calebyyy;

    public Command(Calebyyy calebyyy) {
        this.calebyyy = calebyyy;
    }

    public abstract void execute(String input);
}