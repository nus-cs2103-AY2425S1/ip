public class Parser {
    public boolean isExit(String command) {
        return command.equalsIgnoreCase("bye");
    }

    public String echo(String command) {
        return command;
    }
}
