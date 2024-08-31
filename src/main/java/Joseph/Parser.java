package Joseph;

public class Parser {

    public enum Command {
        EXIT("bye"),
        LIST("list"),
        HELP("help"),
        MARK("mark "),
        UNMARK("unmark "),
        TODO("todo "),
        DEADLINE("deadline "),
        EVENT("event "),
        DELETE("delete ");

        private final String commandText;

        Command(String commandText) {
            this.commandText = commandText;
        }
    }
}
