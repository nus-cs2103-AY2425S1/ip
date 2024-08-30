package luke.command;

public class Command {
    private final String mark;
    private final String command;
    private final String args;

    public Command(String command) {
        this.mark = "-";
        this.command = command;
        this.args = "";
    }

    public Command(String mark, String command, String args) {
        this.mark = mark;
        this.command = command;
        this.args = args;
    }

    public String getMark() {
        return mark;
    }

    public String getCommand() {
        return command;
    }

    public String getArgs() {
        return args;
    }
}
