public class Command {
    private String command;

    public Command(String command) {
        this.command = command;
    }

    public boolean isLeavingCommand() {
        return command.equals("bye");
    }

    public boolean isListCommand() {
        return command.equals("list");

    }

    public boolean isCompletedCommand() {
        String[] words = command.split("\\s+");
        return words[0].equals("mark");
    }

    public boolean isIncompleteCommand() {
        String[] words = command.split("\\s+");
        return words[0].equals("unmark");
    }
}
