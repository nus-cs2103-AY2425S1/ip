public class Parser {
    public boolean isExit(String command) {
        return command.equalsIgnoreCase("bye");
    }

    public boolean isList(String command) {
        return command.equalsIgnoreCase("list");
    }

    public String echo(String command) {
        return command;
    }
}
