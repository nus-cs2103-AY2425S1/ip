public class MissingArgumentException extends LevelHundredException {
    private String command;
    private String arg;
    public MissingArgumentException(String command, String arg) {
        super("Missing inputs for command");
        this.command = command;
        this.arg = arg;
    }

    @Override
    public String toString() {
        return super.toString() + " " + this.command + ": " + this.arg;
    }
}
