import java.util.Arrays;

public class Parser {
    protected Storage storage;
    protected TaskList tasks;
    protected Ui ui;
    protected boolean isExit;
    enum CommandType {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, INVALID
    }

    public Parser(Storage storage, TaskList tasks, Ui ui) {
        this.storage = storage;
        this.tasks = tasks;
        this.ui = ui;
        this.isExit = false;
    }

    public boolean isExit() {
        return isExit;
    }

    public Command parse(String command) throws PikappiException {
        switch (command.split(" ")[0].toUpperCase()) {
        case "BYE":
            isExit = true;
            return new ExitCommand();
        case "LIST":
            return new ListCommand();
        case "MARK":
            return new MarkCommand(Integer.parseInt(command.split(" ")[1]));
        case "UNMARK":
            return new UnmarkCommand(Integer.parseInt(command.split(" ")[1]));
        case "TODO":
            try {
                return new TodoCommand(command.split("todo ")[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new PikappiException("Pika..? What is the task?");
            }
        case "DEADLINE":
            try {
                return new DeadlineCommand(command.split("deadline ")[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new PikappiException("Pika..? What is the task?");
            }
        case "EVENT":
            try {
                return new EventCommand(command.split("event ")[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new PikappiException("Pika..? What is the task?");
            }
        case "DELETE":
            return new DeleteCommand(Integer.parseInt(command.split(" ")[1]));
        default:
            throw new PikappiException("Pika..?? I don't understand..");
        }
    }
}
