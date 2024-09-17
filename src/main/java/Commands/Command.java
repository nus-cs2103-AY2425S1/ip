package Commands;

public abstract class Command {

    public String cmd;
    public Command(String c) {
        this.cmd = c;
    }

    public abstract String commandAction();

    public String getCommand() {
        return this.cmd;
    }
}
